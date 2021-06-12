package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/covid.db";
    private static final String DATABASE2 = "jdbc:sqlite:database/fixed.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    public ArrayList<String> getDefaultData() {
        ArrayList<String> allData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(newCases);
                allData.add(newDeaths);
                allData.add(newPopulation);
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
    public ArrayList<String> getDefaultDataStates() {
        ArrayList<String> allData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country of State', State.Name AS 'State', SUM(NewCases) AS 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', State.Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM State JOIN StateRecords ON State.ID=StateRecords.StateID JOIN Country ON State.CountryID=Country.ID GROUP BY Country.Name, State.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country of State");
                String stateName = results.getString("State");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(stateName);
                allData.add(newCases);
                allData.add(newDeaths);
                allData.add(newPopulation);
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
    public ArrayList<String> getDefaultOrderStates(String order) {
        ArrayList<String> allData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country of State', State.Name AS 'State', SUM(NewCases) AS 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', State.Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM State JOIN StateRecords ON State.ID=StateRecords.StateID JOIN Country ON State.CountryID=Country.ID GROUP BY Country.Name, State.Name ORDER BY SUM(NewDeaths) * 1000000000000/State.Population " + order + ", SUM(NewCases) * 1000000000000/State.Population " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country of State");
                String stateName = results.getString("State");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(stateName);
                allData.add(newCases);
                allData.add(newDeaths);
                allData.add(newPopulation);
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
    public ArrayList<String> getDefaultOrder(String order) {
        ArrayList<String> allData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID GROUP BY Country.Name ORDER BY SUM(NewDeaths) * 1000000000000/Population " + order + ", SUM(NewCases) * 1000000000000/Population " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(newCases);
                allData.add(newDeaths);
                allData.add(newPopulation);
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
    public ArrayList<String> getDateDataStates(String Date1, String Date2) {
        ArrayList<String> dateData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country of State', State.Name AS 'State', SUM(NewCases) AS 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', State.Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM State JOIN StateRecords ON State.ID=StateRecords.StateID JOIN Country ON State.CountryID=Country.ID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name, State.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country of State");
                String stateName = results.getString("State");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                dateData.add(countryName);
                dateData.add(stateName);
                dateData.add(newCases);
                dateData.add(newDeaths);
                dateData.add(newPopulation);
                dateData.add(maxCases);
                dateData.add(maxDate);
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
        return dateData;
    }
    public ArrayList<String> getDateData(String Date1, String Date2) {
        ArrayList<String> dateData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                dateData.add(countryName);
                dateData.add(newCases);
                dateData.add(newDeaths);
                dateData.add(newPopulation);
                dateData.add(maxCases);
                dateData.add(maxDate);
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
        return dateData;
    }
    public ArrayList<String> getStandardOrder(String order, String Date1, String Date2) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name ORDER BY SUM(NewDeaths) * 1000000000000/Population " + order + ", SUM(NewCases) * 1000000000000/Population " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                orderData.add(countryName);
                orderData.add(newCases);
                orderData.add(newDeaths);
                orderData.add(newPopulation);
                orderData.add(maxCases);
                orderData.add(maxDate);
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
        return orderData;
    }
    public ArrayList<String> getStandardOrderStates(String order, String Date1, String Date2) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country of State', State.Name AS 'State', SUM(NewCases) AS 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', State.Population AS 'Population', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM State JOIN StateRecords ON State.ID=StateRecords.StateID JOIN Country ON State.CountryID=Country.ID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name, State.Name ORDER BY SUM(NewDeaths) * 1000000000000/State.Population " + order + ", SUM(NewCases) * 1000000000000/State.Population " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country of State");
                String stateName = results.getString("State");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String newPopulation = results.getString("Population");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                orderData.add(countryName);
                orderData.add(stateName);
                orderData.add(newCases);
                orderData.add(newDeaths);
                orderData.add(newPopulation);
                orderData.add(maxCases);
                orderData.add(maxDate);
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
        return orderData;
    }
    //CUMULATIVE PAGE STARTS HERE
    public ArrayList<String> getCountries() {
        ArrayList<String> str_list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name from country order by name asc";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String name = results.getString("name");
                str_list.add(name);
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
        return str_list;
    }
}
