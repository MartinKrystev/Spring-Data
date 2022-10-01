package dbAppIntroductionExercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P02GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt = connection.prepareStatement("SELECT \n" +
                "\tv.name,\n" +
                "    COUNT(mv.minion_id) AS count_minions\n" +
                "FROM villains AS v\n" +
                "\tJOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                "    JOIN minions AS m ON mv.minion_id = m.id\n" +
                "GROUP BY v.id\n" +
                "HAVING count_minions > 15\n" +
                "ORDER BY count_minions DESC");

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String dbName = rs.getString("name");
            int dbCountMinions = rs.getInt("count_minions");

            System.out.println(dbName + " " + dbCountMinions);
        }
        connection.close();

    }
}
