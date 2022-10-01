package dbAppIntroductionExercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P03GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);


        PreparedStatement villainStmt = connection.prepareStatement("SELECT name FROM villains WHERE id = ?");
        int villainId = Integer.parseInt(scanner.nextLine());

        villainStmt.setInt(1, villainId);

        ResultSet rs = villainStmt.executeQuery();

        if (!rs.next()) {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }

        String villainName = rs.getString("name");
        System.out.println("Villain: " + villainName);

        PreparedStatement minionStmt = connection.prepareStatement(
                "SELECT m.name,  m.age" +
                " FROM villains AS v" +
                " JOIN minions_villains AS mv ON v.id = mv.villain_id" +
                " JOIN minions AS m ON mv.minion_id = m.id" +
                " WHERE v.id = ?;");
        minionStmt.setInt(1, villainId);

        ResultSet minionsRs = minionStmt.executeQuery();

        for (int i = 1; minionsRs.next(); i++) {
            String minionName = minionsRs.getString("m.name");
            int minionAge = minionsRs.getInt("m.age");

            System.out.printf("%d. %s %d", i, minionName, minionAge);
            System.out.println();
        }
        connection.close();

    }
}
