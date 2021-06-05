package app;

import java.util.ArrayList;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class test {

    public static void main(String[] args) {
       JDBCConnection jdbc = new JDBCConnection();
       ArrayList<String> global_data_deaths = jdbc.getGlobalDataDeaths();
       System.out.println(global_data_deaths.size());
       ArrayList<String> global_data_cases = jdbc.getGlobalDataCases();
       System.out.println(global_data_cases.size());
    }
}