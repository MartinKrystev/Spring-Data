package dbAppIntroductionExercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class P05ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        String countryName = scanner.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement(
                "UPDATE towns SET name = UPPER(`name`) WHERE country LIKE ?");
        updateTownNames.setString(1, countryName);
        int updateCount = updateTownNames.executeUpdate();

        if (updateCount == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.println(updateCount + " town names were affected.");

        PreparedStatement selectTowns = connection.prepareStatement(
                "SELECT `name` FROM towns WHERE country LIKE ?");
        selectTowns.setString(1, countryName);
        ResultSet townsSet = selectTowns.executeQuery();

        List<String> townsList = new ArrayList<>();
        while (townsSet.next()) {
            String townName = townsSet.getString("name");
            townsList.add(townName);
        }

        System.out.println(townsList);
        connection.close();

    }
}
