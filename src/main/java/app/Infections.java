package app;

import java.util.ArrayList;

import java.time.LocalDate;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jdk.javadoc.internal.tool.resources.javadoc;

public class infections implements Handler {
    
    public static final String URL = "/infections.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String infectionsvar = "<html lang=\"en\">";
        infectionsvar = infectionsvar +"<head>";
        infectionsvar = infectionsvar +"    <meta charset=\"UTF-8\">";
        infectionsvar = infectionsvar +"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        infectionsvar = infectionsvar +"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        infectionsvar = infectionsvar +"    <title>CovidWebsite</title>";
        infectionsvar = infectionsvar +"    <link rel=\"stylesheet\" href=\"main.css\">";
        infectionsvar = infectionsvar +"</head>";
        infectionsvar = infectionsvar +"<body>";
        infectionsvar = infectionsvar +"    <div class=\"navbar\">";
        infectionsvar = infectionsvar +"        <img id=\"mobile-cta\" class=\"mobile-menu\" src=\"menu.svg\" alt=\"Open Navigation\">    ";
        infectionsvar = infectionsvar +"        <nav>";
        infectionsvar = infectionsvar +"            <img id=\"mobile-exit\" class=\"mobile-menu-exit\" src=\"exit.svg\" alt=\"Close Navigation\">" ;
        infectionsvar = infectionsvar +"            <ul class='nav-ul'>";
        infectionsvar = infectionsvar +"                <li class='btn-lvl1'><a href=\"bigpicture.html\">Big Picture</a></li>";
        infectionsvar = infectionsvar +"                <li class='btn-lvl2 highlight-red'><a href=\"infections.html\">Infections</a></li>";
        infectionsvar = infectionsvar +"                <li class='btn-lvl2'><a href=\"deaths.html\">Deaths</a></li>";
        infectionsvar = infectionsvar +"                <li class='btn-lvl3'><a href=\"cumulative.html\">Cumulative</a></li>";
        infectionsvar = infectionsvar +"                <li class='btn-lvl3'><a href=\"similar.html\">Similar</a></li>";
        infectionsvar = infectionsvar +"            </ul>";
        infectionsvar = infectionsvar +"        </nav>";
        infectionsvar = infectionsvar +"    </div>";
        infectionsvar = infectionsvar +"    ";
        infectionsvar = infectionsvar +"   <form action='/infections.html' method='post'>";
        infectionsvar = infectionsvar +"    <section class=\"shallowglancehero\">";
        infectionsvar = infectionsvar +"        <div class=\"shallowglanceleft-col\">";
        infectionsvar = infectionsvar +"            <h1 class=\"lefttext\">This page offers relevant data on COVID-19 cases. This data can be expressed in Descending or Ascending order if the user wishes.";
        infectionsvar = infectionsvar +"            <h4 class=\"datetext\">Below you will also see two boxes where you can input two dates. If you would like to see data between two dates, enter those two dates down below, with the earlier date on the left."; 
        infectionsvar = infectionsvar +"            <h4 class=\"datetext\">Once you have made a selection, press the 'GO' button to have your desired data displayed to you.";
        infectionsvar = infectionsvar +"            <h4 class=\"notetext\">Note that dates not in the format YYYY-MM-DD, not between 2020-01-22 to 2021-04-22 inclusive, or are otherwise not correct/are blank, will result in default data. This will also occur if no selection is made in the dropdown box.</h1>";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"              <select id='shalloworder_drop' name='shalloworder_drop'>";
        infectionsvar = infectionsvar +"                  <option></option>";
        infectionsvar = infectionsvar +"                  <option>Descending Order</option>";
        infectionsvar = infectionsvar +"                  <option>Ascending Order</option>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control1\"' id='date1_textbox' name='date1_textbox' placeholder='YYYY-MM-DD'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control2\"' id='date2_textbox' name='date2_textbox' placeholder='YYYY-MM-DD'>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <button type='submit' class='\"btn btn-shallow\"'>Go</button>";
        infectionsvar = infectionsvar +"        </div>";
        infectionsvar = infectionsvar +"   </form>";
        infectionsvar = infectionsvar +"    </section>";

        String date1_textbox = context.formParam("date1_textbox");
        String date2_textbox = context.formParam("date2_textbox");
        String shalloworder_drop = context.formParam("shalloworder_drop");
        
        String s = "2020-01-22";
        String e = "2021-04-22";
        LocalDate start = LocalDate.parse(s);
        LocalDate end = LocalDate.parse(e);
        ArrayList<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        LocalDate firstDate = LocalDate.parse(date1_textbox);
        LocalDate secondDate = LocalDate.parse(date2_textbox);
        if (((date1_textbox == null || date1_textbox == "") || (date2_textbox == null || date2_textbox == "")) && (shalloworder_drop == null || shalloworder_drop == "")) {
            infectionsvar = infectionsvar + doNothing();
        }
        else if (((date1_textbox != null || date1_textbox != "") && (date2_textbox != null || date2_textbox != "")) && (shalloworder_drop == null || shalloworder_drop == "")) {
            int j;
            for (j = 0; j < totalDates.size() - 1; j+=1) {
                if (((date1_textbox.equals(totalDates.get(j)) || (date2_textbox.equals(totalDates.get(j))) && (shalloworder_drop == null || shalloworder_drop == "")))) {
                    infectionsvar = infectionsvar + outputDate(date1_textbox, date2_textbox);
                    break;
                }
                else {
                    infectionsvar = infectionsvar + doNothing();
                    break;
                }
            }
        }
        else {
            infectionsvar = infectionsvar + outputDate(date1_textbox, date2_textbox);
        }
        infectionsvar = infectionsvar + "</body>" + "</html>";
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(infectionsvar);
    }
    public String doNothing() {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2>COVID-19 Default Data</h2>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDefaultData();
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        infectionsvar = infectionsvar + "<table style=width:100%>";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=4) {
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        return infectionsvar;
    }

    public String outputDate(String date1, String date2) {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2>COVID-19 Data Between 2 Dates</h2>";
        
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDateData(date1, date2);
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        infectionsvar = infectionsvar + "<table style=width:100%>";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=4) {
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        return infectionsvar;
    }
}