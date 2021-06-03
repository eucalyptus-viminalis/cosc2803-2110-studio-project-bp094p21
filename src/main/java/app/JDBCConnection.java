package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies Database
 * This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/covid.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<String> getDefaultData() {
        ArrayList<String> allData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM COUNTRY JOIN Country_RegionCases ON Country.ID=Country_RegionCases.Country_RegionID GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(newCases);
                allData.add(maxCases);
                allData.add(maxDate);
            }
            statement.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try{
                if (connection != null) {
                    connection.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return allData;
    }
}
