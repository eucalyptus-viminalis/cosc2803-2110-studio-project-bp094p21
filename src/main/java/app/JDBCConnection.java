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
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country_RegionCases NATURAL JOIN Country_RegionDeaths JOIN Country ON Country_RegionCases.Country_RegionID=Country.ID GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(newCases);
                allData.add(newDeaths);
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
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country_RegionCases NATURAL JOIN Country_RegionDeaths JOIN Country ON Country_RegionCases.Country_RegionID=Country.ID GROUP BY Country.Name ORDER BY SUM(NewDeaths) " + order + ", SUM(NewCases) " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                allData.add(countryName);
                allData.add(newCases);
                allData.add(newDeaths);
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
    public ArrayList<String> getDateData(String Date1, String Date2) {
        ArrayList<String> dateData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country_RegionCases NATURAL JOIN Country_RegionDeaths JOIN Country ON Country_RegionCases.Country_RegionID=Country.ID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                dateData.add(countryName);
                dateData.add(newCases);
                dateData.add(newDeaths);
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
    // JIN's Methods
    public ArrayList<String> getCountryNames() {
        ArrayList<String> str_list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
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
    public ArrayList<String> getStateNames() {
        ArrayList<String> str_list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name from state order by name asc";
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
    public int getPastYearCasesCountryTotal() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(c) s from (select name, sum(newcases) c from country join country_regioncases on country.id = country_regioncases.country_regionid natural join date where date between '2020-04-22' and '2021-04-22' group by name order by sum(newcases) desc)";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("s");
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
        return sum;
    }
    public int getPastYearCasesStateTotal() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(c) s from (select name, sum(newcases) c from state join province_statecases on state.id = province_statecases.province_stateid natural join date where date between '2020-04-22' and '2021-04-22' group by name order by sum(newcases) desc)";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("s");
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
        return sum;
    }
    public String getPastMonthDeathsCountryTop1Name() {
        String name = new String();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name n, sum(newdeaths) s from country join country_regiondeaths on country.id = country_regiondeaths.country_regionid natural join date where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc limit 1";
            ResultSet result = statement.executeQuery(query);
            name = result.getString("n");
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
        return name;
    }
    public int getPastMonthDeathsCountryTop1Sum() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name n, sum(newdeaths) s from country join country_regiondeaths on country.id = country_regiondeaths.country_regionid natural join date where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc limit 1";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("s");
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
        return sum;
    }
    public int getPastMonthDeathsCountryTotal() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(s) s from (select name, sum(newdeaths) s from country join country_regiondeaths on country.id = country_regiondeaths.country_regionid natural join date where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc)";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("s");
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
        return sum;
    }
    public ArrayList<String> getPastWeekNoCasesCountry() {
        ArrayList<String> names = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name, newcases from country join country_regioncases on country.id = country_regioncases.country_regionid where date between '2021-04-16' and '2021-04-22' group by name having sum(newcases) = 0 order by name asc";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String name     = results.getString("name");
                names.add(name);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return names;
    }
    public int getPastWeekNoCasesStateCount() {
        int count = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select count(*) c from (select name, newcases from state join province_statecases on state.id = province_statecases.province_stateid where date between '2020-04-16' and '2020-04-22' group by name having sum(newcases) = 0)";
            ResultSet result = statement.executeQuery(query);
            count = result.getInt("c");
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
        return count;
    }
    public ArrayList<String> getStandardOrder(String order, String Date1, String Date2) {
        ArrayList<String> orderData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', SUM(NewDeaths) AS 'Total Deaths', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM Country_RegionCases NATURAL JOIN Country_RegionDeaths JOIN Country ON Country_RegionCases.Country_RegionID=Country.ID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name ORDER BY SUM(NewDeaths) " + order + ", SUM(NewCases) " + order + "";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String newDeaths = results.getString("Total Deaths");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                orderData.add(countryName);
                orderData.add(newCases);
                orderData.add(newDeaths);
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
    public ArrayList<String> getGlobalData() {
        ArrayList<String> global_data = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name, sum(newdeaths) d, sum(newcases) c from country join countryrecords on country.id = countryrecords.countryid group by name order by name asc limit 8";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String name     = results.getString("name");
                String deaths = results.getString("d");
                String cases = results.getString("c");
                global_data.add(name);
                global_data.add(deaths);
                global_data.add(cases);
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return global_data;
    }   

}
