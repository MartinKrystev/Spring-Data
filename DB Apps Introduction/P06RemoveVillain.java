package dbAppIntroductionExercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P06RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?");
        selectVillain.setInt(1, villainId);
        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println("No such villain was found");
            return;
        }

        String villainName = villainSet.getString("name");

        PreparedStatement selectVillainsMinions = connection.prepareStatement(
                "SELECT COUNT(DISTINCT minion_id) AS countMinions" +
                    " FROM minions_villains WHERE villain_id = ?");
        selectVillainsMinions.setInt(1, villainId);
        ResultSet villainsMinionsSet = selectVillainsMinions.executeQuery();
        villainsMinionsSet.next();

        int minionsDeleted = villainsMinionsSet.getInt("countMinions");

        connection.setAutoCommit(false);
        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?");
            deleteMinionsVillains.setInt(1, villainId);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillains = connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?");
            deleteVillains.setInt(1, villainId);
            deleteVillains.executeUpdate();

            connection.commit();
            System.out.println(villainName + " was deleted");
            System.out.println(minionsDeleted + " minions released");

        } catch (SQLException e) {
            connection.rollback();
        }

        connection.close();
    }
}
