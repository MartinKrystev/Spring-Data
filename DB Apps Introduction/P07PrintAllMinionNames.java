package dbAppIntroductionExercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class P07PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement selectAllMinions = connection.prepareStatement(
                "SELECT name FROM minions");
        ResultSet allMinionsSet = selectAllMinions.executeQuery();

        List<String> minionsInOrder = new ArrayList<>();
        while (allMinionsSet.next()) {
            minionsInOrder.add(allMinionsSet.getString("name"));
        }

        for (int i = 0; i < minionsInOrder.size() / 2; i++) {
            System.out.println(minionsInOrder.get(i));
            System.out.println(minionsInOrder.get(minionsInOrder.size() - 1 - i));
        }
        if (minionsInOrder.size() % 2 != 0) {
            System.out.println(minionsInOrder.get(minionsInOrder.size() / 2));
        }

        connection.close();
    }
}
