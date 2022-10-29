package dbAppIntroductionExercise;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class P08IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        Scanner scanner = new Scanner(System.in);

        List<Integer> inputIDs = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());

        // Building the query with the proper amount of parameters
        String updateQuery = buildQuery(inputIDs);

        PreparedStatement updateMinions = connection.prepareStatement(updateQuery);

        for (int i = 1; i <= inputIDs.size(); i++) {
            updateMinions.setInt(i,inputIDs.get(i - 1));
        }
        updateMinions.executeUpdate();

        PreparedStatement selectMinions = connection.prepareStatement(
                " SELECT * FROM minions");
        ResultSet minionsSet = selectMinions.executeQuery();

        while (minionsSet.next()) {
            String minionID = minionsSet.getString("id");
            String minionName = minionsSet.getString("name");
            String minionAge = minionsSet.getString("age");
            System.out.printf("%s %s %s%n", minionID, minionName, minionAge);
        }

        connection.close();
    }

    private static String buildQuery(List<Integer> inputIDs) {
        String updateQuery = "UPDATE minions SET age = age + 1, name = LOWER(name) WHERE id IN (";
        String temp = "";
        for (int i = 0; i < inputIDs.size(); i++) {
            temp += ",?";
        }

        temp = temp.replaceFirst(",","");
        temp += ")";
        updateQuery += temp;
        return updateQuery;
    }
}
