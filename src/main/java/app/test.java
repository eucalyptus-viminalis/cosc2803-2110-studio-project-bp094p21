package app;

import java.util.ArrayList;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class test {

    public static void main(String[] args) {
       JDBCConnection jdbc = new JDBCConnection();
       ArrayList<String> global_data = jdbc.getGlobalData();
       ArrayList<String> ndr_data = new ArrayList<String>();
       int i;
       for (i = 0; i < 8; i++) {
           ndr_data.add(global_data.get(i*3));
           ndr_data.add(global_data.get((i*3)+1));
           int ratio = (100 * Integer.parseInt(global_data.get((i*3)+1))) / Integer.parseInt(global_data.get((i*3)+2));
           ndr_data.add(Integer.toString(ratio));
       }
       System.out.println(ndr_data);
    }
}