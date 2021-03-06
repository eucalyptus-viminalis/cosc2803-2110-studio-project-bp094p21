package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnection {

    // Name of database file (contained in database folder)
    // private static final String DATABASE = "jdbc:sqlite:database/covid.db";
    private static final String DATABASE2 = "jdbc:sqlite:database/final.db";

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
    public ArrayList<String> getCountryLatitudeExtension(String Country) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Latitude + 5 AS 'Max Lat', Latitude - 5 AS 'Min Lat' FROM Country WHERE Name = '" + Country + "'";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String maxLat = results.getString("Max Lat");
                String minLat = results.getString("Min Lat");
                orderData.add(maxLat);
                orderData.add(minLat);
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
    public ArrayList<String> getLatitudeAndLongitude(String Country) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Latitude AS 'Lat', Longitude AS 'Long' FROM Country WHERE Name = '" + Country + "'";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String Lat = results.getString("Lat");
                String Long = results.getString("Long");
                orderData.add(Lat);
                orderData.add(Long);
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
    public ArrayList<String> getDistance(String Date1, String Date2, String lati, String longi, int dist, String country) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Name as 'Country Name', printf('%.5f', (100.0 * SUM(NewCases)/Population)) AS 'Infection Rate', (6371 * acos ( cos ( radians('" + lati + "') ) * cos( radians( Latitude) ) * cos( radians ( Longitude ) - radians('" + longi + "') ) + sin (radians('" + lati + "') ) * sin( radians( Latitude ) ) ) ) AS distance FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE (distance < " + dist + ") AND (Date BETWEEN '" + Date1 + "' AND '" + Date2 + "') AND (Country.Name != '" + country + "') GROUP BY Country.Name ORDER BY distance";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String infectionRate = results.getString("Infection Rate");
                String finalDistance = results.getString("distance");
                orderData.add(countryName);
                orderData.add(infectionRate);
                orderData.add(finalDistance);
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
    public ArrayList<String> getSimilarClimates(String Date1, String Date2, String minLat, String maxLat) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Name AS 'Country Name', printf('%.5f', (100.0 * SUM(NewCases)/Population)) AS 'Transmission Rate', printf('%.5f', (100.0 * SUM(NewDeaths)/Population)) AS 'Death Rate' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE (Country.Latitude BETWEEN '" + maxLat + "' AND '" + minLat + "') AND (Date BETWEEN '" + Date1 + "' AND '" + Date2 + "') GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String transRate = results.getString("Transmission Rate");
                String deathRate = results.getString("Death Rate");
                orderData.add(countryName);
                orderData.add(transRate);
                orderData.add(deathRate);
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
    public ArrayList<String> getInfDeaRatio(String Date1, String Date2, String country) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases)/SUM(NewDeaths) AS 'Infection to Death Ratio', Population/(Sum(NewCases) + SUM(NewDeaths)) AS 'Death to Population Ratio' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE (Country.Name = '" + country + "') AND (Date BETWEEN '" + Date1 + "' AND '" + Date2 + "')";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String infToDeaRate = results.getString("Infection to Death Ratio");
                String deaToPopRate = results.getString("Death to Population Ratio");
                orderData.add(countryName);
                orderData.add(infToDeaRate);
                orderData.add(deaToPopRate);
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
    public ArrayList<String> getLeastRecovered() {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Name AS 'Country Name', SUM(NewCases) AS 'Cases', SUM(NewDeaths) AS 'Deaths' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE Date BETWEEN '2021-04-15' AND '2021-04-22' GROUP BY Country.Name ORDER BY SUM(NewCases) DESC, SUM(NewDeaths) DESC limit 1";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String cases = results.getString("Cases");
                String deaths = results.getString("Deaths");
                orderData.add(countryName);
                orderData.add(cases);
                orderData.add(deaths);
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
    public ArrayList<String> getMostRecovered() {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Name AS 'Country Name', SUM(NewCases) AS 'Cases', SUM(NewDeaths) AS 'Deaths' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID WHERE Date BETWEEN '2021-04-15' AND '2021-04-22' GROUP BY Country.Name ORDER BY SUM(NewCases) ASC, SUM(NewDeaths) ASC limit 1";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String cases = results.getString("Cases");
                String deaths = results.getString("Deaths");
                orderData.add(countryName);
                orderData.add(cases);
                orderData.add(deaths);
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
    public ArrayList<String> getHighRates() {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', printf('%.0f', 1000000 * SUM(NewCases)/Population) AS 'Infections Per 1 Million People' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID GROUP BY Country.Name ORDER BY 1000000 * SUM(NewCases)/Population DESC limit 5";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String infections = results.getString("Infections Per 1 Million People");
                orderData.add(countryName);
                orderData.add(infections);
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
    public ArrayList<String> getLowRates() {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', printf('%.0f', 1000000 * SUM(NewDeaths)/Population) AS 'Infections Per 1 Million People' FROM Country JOIN CountryRecords ON Country.ID=CountryRecords.CountryID GROUP BY Country.Name ORDER BY 1000000 * SUM(NewDeaths)/Population DESC limit 5";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String infections = results.getString("Infections Per 1 Million People");
                orderData.add(countryName);
                orderData.add(infections);
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
}
