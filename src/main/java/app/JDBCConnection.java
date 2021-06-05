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

    public ArrayList<String> getDateData(String Date1, String Date2) {
        ArrayList<String> dateData = new ArrayList<String>();

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT Country.Name AS 'Country Name', SUM(NewCases) as 'Total Cases', MAX(NewCases) AS 'Most Cases in a Day', Date AS 'Date of Most Cases' FROM COUNTRY JOIN Country_RegionCases ON Country.ID=Country_RegionCases.Country_RegionID WHERE Date BETWEEN '" + Date1 + "' AND '" + Date2 + "' GROUP BY Country.Name";
            System.out.println(query);
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String countryName = results.getString("Country Name");
                String newCases = results.getString("Total Cases");
                String maxCases = results.getString("Most Cases in a Day");
                String maxDate = results.getString("Date of Most Cases");
                dateData.add(countryName);
                dateData.add(newCases);
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
    public int getTotalDeathsInDateRange1Country(String countryName, String fromDate, String toDate) {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(newdeaths) s from country_regiondeaths natural join date join country on country.id = country_regiondeaths.country_regionid where country.name = '" + countryName + "' and date between '" + fromDate + "' and '" + toDate +"'";
            System.out.println(query);
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
    public int getTotalCasesInDateRange1Country(String countryName, String fromDate, String toDate) {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(newcases) s from country_regioncases natural join date join country on country.id = country_regioncases.country_regionid where country.name = '" + countryName + "' and date between '" + fromDate + "' and '" + toDate + "'";
            System.out.println(query);
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
    public int getTotalDeathsCountryFullRange() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(country_regiondeaths.newdeaths) sum from country_regiondeaths natural join date where date between '2020-01-22' and '2021-04-22'";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("sum");
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
    public int getTotalDeathsStateFullRange() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(province_statedeaths.newdeaths) sum from province_statedeaths natural join date where date between '2020-01-22' and '2021-04-22'";
            ResultSet result = statement.executeQuery(query);
            sum = result.getInt("sum");
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
    public ArrayList<String> getPeakData(String countryName) {
        ArrayList<String> peak_data = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select max(newdeaths) d, date date from country join country_regiondeaths on country.id = country_regiondeaths.country_regionid natural join date where name = '" + countryName + "'";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String deaths     = results.getString("d");
                String date = results.getString("date");
                peak_data.add(date);
                peak_data.add(deaths);
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
        return peak_data;
    }
    public int getCountryPopulation(String countryName) {
        int pop = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select population p from country where name ='" + countryName + "'";
            ResultSet result = statement.executeQuery(query);
            pop = result.getInt("p");
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
        return pop;
    }
}
