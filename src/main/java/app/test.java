package app;

import java.util.ArrayList;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class test {

    public static void main(String[] args) {
       JDBCConnection jdbc = new JDBCConnection();
       ArrayList<String> country_names = jdbc.getCountryNames();
       System.out.println(country_names.size());
    }
}