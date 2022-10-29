package dbAppIntroductionExercise;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class P09IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);
        int inputMinionID = Integer.parseInt(scanner.nextLine());

        CallableStatement increaseMinionAge = connection.prepareCall(
                "CALL usp_get_older(?)");

        increaseMinionAge.setInt(1, inputMinionID);
        increaseMinionAge.executeQuery();

        PreparedStatement selectUpdatedMinion = connection.prepareStatement(
                "SELECT name, age FROM minions WHERE id = ?");
        selectUpdatedMinion.setInt(1, inputMinionID);
        ResultSet minionSet = selectUpdatedMinion.executeQuery();

        while (minionSet.next()) {
            String minionName = minionSet.getString("name");
            String minionAge = minionSet.getString("age");
            System.out.println(minionName + " " + minionAge);
        }

        connection.close();
    }
}
