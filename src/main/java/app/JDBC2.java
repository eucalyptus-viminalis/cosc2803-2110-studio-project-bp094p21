package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC2 {
    private static final String DATABASE2 = "jdbc:sqlite:database/fixed.db";
    public JDBC2() {
        System.out.println("Created JDBC 2 Object");
    }
    // Big Picture
    public ArrayList<String> getCountryNames() {
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
    public ArrayList<String> getStateNames() {
        ArrayList<String> str_list = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE2);
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
    public int getPastYearTotalCasesCountry() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(c) s from (select name, sum(newcases) c from country join countryrecords on country.id = countryrecords.countryid where date between '2020-04-22' and '2021-04-22' group by name order by sum(newcases) desc)";
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
    public int getPastYearTotalCasesState() {
        int sum = 0;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(c) s from (select name, sum(newcases) c from state join staterecords on state.id = staterecords.stateid where date between '2020-04-22' and '2021-04-22' group by name order by sum(newcases) desc)";
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
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name n, sum(newdeaths) s from country join countryrecords on country.id = countryrecords.countryid where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc limit 1";
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
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name n, sum(newdeaths) s from country join countryrecords on country.id = countryrecords.countryid where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc limit 1";
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
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select sum(d) s from (select name, sum(newdeaths) d from country join countryrecords on country.id = countryrecords.countryid where date between '2021-03-22' and '2021-04-22' group by name order by sum(newdeaths) desc)";
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
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select name, newcases from country join countryrecords on country.id = countryrecords.countryid where date between '2021-04-16' and '2021-04-22' group by name having sum(newcases) = 0 order by name asc";
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
            connection = DriverManager.getConnection(DATABASE2);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "select count(name) c from (select name from state join staterecords on state.id = staterecords.stateid where date between '2021-04-16' and '2021-04-22' group by name having sum(newcases) = 0)";
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
    
    // Deaths
    public int getCountryPopulation(String country) {
      int count = 0;
      Connection connection = null;
      try {
          connection = DriverManager.getConnection(DATABASE2);
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);
          String query = "select population from country where name ='" + country + "'";
          ResultSet result = statement.executeQuery(query);
          count = result.getInt("population");
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
   public int getTotalCasesInDateRange1Country(String country, String from, String to) {
      int count = 0;
      Connection connection = null;
      try {
          connection = DriverManager.getConnection(DATABASE2);
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);
          String query = "select sum(newcases) s from country join countryrecords on country.id = countryrecords.countryid where date between '" + from + "' and '" + to + "' and name = '" + country + "'";
          ResultSet result = statement.executeQuery(query);
          count = result.getInt("s");
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
    public ArrayList<String> getPeakData(String country) {

      ArrayList<String> data = new ArrayList<String>();
      Connection connection = null;
      try {
          connection = DriverManager.getConnection(DATABASE2);
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);
          String query = "select name, max(newdeaths) m, date d from country join countryrecords on country.id = countryrecords.countryid where name = '" + country + "'";
          ResultSet results = statement.executeQuery(query);
          while (results.next()) {
              String date     = results.getString("d");
              String deaths     = results.getString("m");
              data.add(date);
              data.add(deaths);
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
      return data;
  }
    public int getTotalDeathsInDateRange1Country(String country, String from, String to) {
      int count = 0;
      Connection connection = null;
      try {
          connection = DriverManager.getConnection(DATABASE2);
          Statement statement = connection.createStatement();
          statement.setQueryTimeout(30);
          String query = "select sum(newdeaths) s from country join countryrecords on country.id = countryrecords.countryid where date between '" + from + "' and '" + to + "' and name = '" + country + "'";
          ResultSet result = statement.executeQuery(query);
          count = result.getInt("s");
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
