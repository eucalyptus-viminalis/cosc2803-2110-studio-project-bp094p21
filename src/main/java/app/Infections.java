package app;

import java.util.ArrayList;

import java.time.LocalDate;
import io.javalin.http.Context;
import io.javalin.http.Handler;

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
        infectionsvar = infectionsvar +"            <h1 class=\"lefttext\">Welcome to the Infections Page!";
        infectionsvar = infectionsvar +"            <h4 class=\"datetext\">On this page you will be presented with a table of data that displays some basic useful information on COVID-19 cases and deaths per country/state. For more information on deaths, visit the 'Deaths' page."; 
        infectionsvar = infectionsvar +"            <h4 class=\"datetext\">You will also be able to make adjustments to the dates the data is presented between, whether country or states are presented, or whether the data is displayed by worst affected/least affected or not. Scroll down to have a look!";
        infectionsvar = infectionsvar +"            <h4 class=\"datetext\">Note: Worst affected means the highest death rates followed by the highest infection rates per capita.";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"              <select id='shalloworder_drop' name='shalloworder_drop'>";
        infectionsvar = infectionsvar +"                  <option></option>";
        infectionsvar = infectionsvar +"                  <option>Descending Order (Worst Affected First)</option>";
        infectionsvar = infectionsvar +"                  <option>Ascending Order (Least Affected First)</option>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control1\"' id='date1_textbox' name='date1_textbox' type='date' value='2020-01-22' min='2020-01-22' max='2021-04-22'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control2\"' id='date2_textbox' name='date2_textbox' type='date' value='2021-04-22' min='2020-01-22' max='2021-04-22'>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <button type='submit' class='\"btn btn-shallow\"'>Go</button>";
        infectionsvar = infectionsvar +"        </div>";
        infectionsvar = infectionsvar +"   </form>";
        infectionsvar = infectionsvar +"    </section>";

        String date1_textbox = context.formParam("date1_textbox");
        String date2_textbox = context.formParam("date2_textbox");
        String shalloworder_drop = context.formParam("shalloworder_drop");
        
        String sta = "2020-01-22";
        String en = "2021-04-22";
        LocalDate start = LocalDate.parse(sta);
        LocalDate end = LocalDate.parse(en);
        ArrayList<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        try {
            LocalDate firstDate = LocalDate.parse(date1_textbox);
            LocalDate secondDate = LocalDate.parse(date2_textbox);
            if (!totalDates.contains(firstDate)) {
                infectionsvar = infectionsvar + "<h4 class=firstboxcheck>Please ensure that the date in your first box is a date in range of 2020-01-22 to 2021-04-22.</h4>";
                infectionsvar = infectionsvar + doNothing();
            }
            if (!totalDates.contains(secondDate)) {
                infectionsvar = infectionsvar + "<h4 class=secondboxcheck>Please ensure that the date in your second box is a date in range of 2020-01-22 to 2021-04-22.</h4>";
                infectionsvar = infectionsvar + doNothing();
            }
            if ((!totalDates.contains(secondDate)) && (!totalDates.contains(firstDate))) {
                infectionsvar = infectionsvar + "<h4 class=secondboxcheck>Please ensure that the date in your first and second box is a date in range of 2020-01-22 to 2021-04-22.</h4>";
                infectionsvar = infectionsvar + doNothing();
            }
            if (shalloworder_drop.equals("Ascending Order (Least Affected First)") || shalloworder_drop.equals("Descending Order (Worst Affected First)")) {
                int a;
                int b;
                for (a = 0; a < totalDates.size(); a++) {
                    for (b = 0; b < totalDates.size(); b++) {
                        if (((firstDate.compareTo(totalDates.get(a)) == 0) && (secondDate.compareTo(totalDates.get(b)) == 0))) {
                            infectionsvar = infectionsvar + testOrder2(shalloworder_drop, firstDate, secondDate);
                            break;
                        }
                    }
                }
            }
            if (((date1_textbox != null || date1_textbox != "") && (date2_textbox != null || date2_textbox != "")) && (shalloworder_drop != "Ascending Order (Least Affected First)") || shalloworder_drop != "Descending Order (Worst Affected First)") {
                int j;
                int k;
                for (j = 0; j < totalDates.size(); j++) {
                    for (k = 0; k < totalDates.size(); k++) {
                        if (((firstDate.compareTo(totalDates.get(j)) == 0) && (secondDate.compareTo(totalDates.get(k)) == 0)) && (shalloworder_drop == null || shalloworder_drop == "")) {
                            infectionsvar = infectionsvar + testOrder(firstDate, secondDate);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            infectionsvar = infectionsvar + doNothing();
        }
        infectionsvar = infectionsvar + "</body>" + "</html>";
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(infectionsvar);
    }
    //This method is called to check the order of dates inputted, and if incorrect, corrects them.
    public String testOrder(LocalDate date1, LocalDate date2) {
        String infectionsvar = "";
        if ((date2.compareTo(date1) < 0)) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            infectionsvar = infectionsvar + outputDate(secondDate, firstDate);
        }
        else if (((date2.compareTo(date1) == 0) || (date2.compareTo(date1) > 0))) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            infectionsvar = infectionsvar + outputDate(firstDate, secondDate);
        }
        else {
            infectionsvar = infectionsvar + doNothing();
        }
        return infectionsvar;
    }
    public String testOrder2(String orderDrop, LocalDate date1, LocalDate date2) {
        String infectionsvar = "";
        if ((date2.compareTo(date1) < 0)) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            infectionsvar = infectionsvar + standardOrder(orderDrop, secondDate, firstDate);
        }
        else if (((date2.compareTo(date1) == 0) || (date2.compareTo(date1) > 0))) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            infectionsvar = infectionsvar + standardOrder(orderDrop, firstDate, secondDate);
        }
        else {
            infectionsvar = infectionsvar + doNothing();
        }
        return infectionsvar;
    }
    //Default Data. This is called upon at the start of the program, when no options are selected, or an error is caught.
    public String doNothing() {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2 class=\"tableheader\">COVID-19 Default Data (2020-01-22 to 2021-04-22) (YYYY-MM-DD) (Alphabetically)</h2>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDefaultData();
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        int rank = 0;
        infectionsvar = infectionsvar + "<div class=\"tablediv\">";
        infectionsvar = infectionsvar + "<table class=\"center\">";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Ranking</th>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Total Deaths</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=5) {
            rank = rank + 1;
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + '#' + rank + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+4) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        infectionsvar = infectionsvar + "</div>";
        return infectionsvar;
    }
    public String standardOrder(String order, String date1, String date2) {
        String infectionsvar = "";
        String newOrder;
        if (order.equals("Descending Order (Worst Affected First)")) {
            newOrder = "DESC";
        }
        else {
            newOrder = "ASC";
        }
        infectionsvar = infectionsvar + "<h2 class=\"tableheader\">COVID-19 data in " + order + " between " + date1 + " and " + date2 + " (YYYY-MM-DD)</h2>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getStandardOrder(newOrder, date1, date2);
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        int rank = 0;
        infectionsvar = infectionsvar + "<div class=\"tablediv\">";
        infectionsvar = infectionsvar + "<table class=\"center\">";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Ranking</th>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Total Deaths</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=5) {
            rank = rank + 1;
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + '#' + rank + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+4) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        infectionsvar = infectionsvar + "</div>";
        return infectionsvar;
    }

    public String outputDate(String date1, String date2) {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2 class=\"tableheader\">COVID-19 data between " + date1 + " and " + date2 + " (YYYY-MM-DD) (Alphabetically)</h2>";
        
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDateData(date1, date2);
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        int rank = 0;
        infectionsvar = infectionsvar + "<div class=\"tablediv\">";
        infectionsvar = infectionsvar + "<table class=\"center\">";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Ranking</th>"; 
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Total Deaths</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=5) {
            rank = rank + 1;
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + '#' + rank + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+4) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        infectionsvar = infectionsvar + "</div>";
        return infectionsvar;
    }
}