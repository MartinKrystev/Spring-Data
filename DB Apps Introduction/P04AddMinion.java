package dbAppIntroductionExercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P04AddMinion {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String[] minionInfo = scanner.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getTown(connection, minionTown);
        int villainId = getVillain(connection, villainName);

        insertMinion(connection, minionName, minionAge, townId);

        int lastMinionId = getLastMinionId(connection);

        insertMinionsVillains(connection, villainId, lastMinionId);

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);

        connection.close();

    }

    private static void insertMinionsVillains(Connection connection, int villainId, int lastMinionId) throws SQLException {
        PreparedStatement insertMinionsVillains = connection.prepareStatement(
                "INSERT INTO minions_villains(minion_id, villain_id) VALUES(?, ?);");
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villainId);
        insertMinionsVillains.executeUpdate();
    }

    private static int getLastMinionId(Connection connection) throws SQLException {
        PreparedStatement getLastMinion = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id DESC;");
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");
        return lastMinionId;
    }

    private static void insertMinion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement insertMinion = connection.prepareStatement(
                "INSERT INTO minions(`name`, age, town_id) VALUES (?, ?, ?);");
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);
        insertMinion.executeUpdate();
    }

    private static int getVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement findVillain = connection.prepareStatement(
                "SELECT * FROM villains WHERE name LIKE ?;");
        findVillain.setString(1, villainName);

        ResultSet villainSet = findVillain.executeQuery();

        int villainId = 0;
        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(
                    "INSERT INTO villains (`name`, evilness_factor) VALUES (?, ?);");
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");

            insertVillain.executeUpdate();

            ResultSet newVillainSet = findVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }

    private static int getTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement findTown = connection.prepareStatement(
                "SELECT * FROM towns WHERE name LIKE ?;");
        findTown.setString(1, minionTown);

        ResultSet townSet = findTown.executeQuery();

        int townId = 0;
        if (!townSet.next()) {
            PreparedStatement insertTown = connection.prepareStatement(
                    "INSERT INTO towns (`name`) VALUES (?);");
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();

            ResultSet newTownSet = findTown.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;
    }
}
