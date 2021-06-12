package app;

import java.util.ArrayList;

import java.time.LocalDate;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class cumulative implements Handler {
    
    public static final String URL = "/cumulative.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String cumulativevar = "<html lang=\"en\">";
        cumulativevar = cumulativevar + "<head>";
        cumulativevar = cumulativevar +"    <meta charset=\"UTF-8\">";
        cumulativevar = cumulativevar +"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">";
        cumulativevar = cumulativevar +"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">";
        cumulativevar = cumulativevar +"    <title>CovidWebsite</title>";
        cumulativevar = cumulativevar +"    <link rel=\"stylesheet\" href=\"main.css\">";
        cumulativevar = cumulativevar +"</head>";
        cumulativevar = cumulativevar +"<body>";
        cumulativevar = cumulativevar +"    <div class=\"navbar\">";
        cumulativevar = cumulativevar +"        <img id=\"mobile-cta\" class=\"mobile-menu\" src=\"menu.svg\" alt=\"Open Navigation\">    ";
        cumulativevar = cumulativevar +"        <nav>";
        cumulativevar = cumulativevar +"            <img id=\"mobile-exit\" class=\"mobile-menu-exit\" src=\"exit.svg\" alt=\"Close Navigation\">" ;
        cumulativevar = cumulativevar +"            <ul class='nav-ul'>";
        cumulativevar = cumulativevar +"                <li class='btn-lvl1'><a href=\"bigpicture.html\">Big Picture</a></li>";
        cumulativevar = cumulativevar +"                <li class='btn-lvl2'><a href=\"infections.html\">Infections</a></li>";
        cumulativevar = cumulativevar +"                <li class='btn-lvl2'><a href=\"deaths.html\">Deaths</a></li>";
        cumulativevar = cumulativevar +"                <li class='btn-lvl3 highlight-red'><a href=\"cumulative.html\">Cumulative</a></li>";
        cumulativevar = cumulativevar +"                <li class='btn-lvl3'><a href=\"similar.html\">Similar</a></li>";
        cumulativevar = cumulativevar +"            </ul>";
        cumulativevar = cumulativevar +"        </nav>";
        cumulativevar = cumulativevar +"    </div>";
        cumulativevar = cumulativevar +"   <form action='/cumulative.html' method='post'>";
        cumulativevar = cumulativevar +"    <section class=\"cumulativehero\">";
        cumulativevar = cumulativevar +"        <div class=\"cumulativeleft-col\">";
        cumulativevar = cumulativevar +"            <h1 class=\"lefttext\">Welcome to the Cumulative Page!";
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">On this page you will be presented with tables of data that display some in depth information on COVID-19 cases and deaths based on country."; 
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">You will also be able to make adjustments to the dates the data is presented between, and how far from a country a comparison is made. Scroll down to have a look!";
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">Please also be aware that if at least one of the date boxes are left empty, the table presented will default to the dates that cover the whole range of data collection (2020-01-22 to 2021-04-22).";
        cumulativevar = cumulativevar +"            <div class='\"form-group\"'>";
        cumulativevar = cumulativevar +"                  <h4 class=\"textabovedays\">Please select a country of reference for cumulative data to be presented to you:";
        cumulativevar = cumulativevar +"              <select id='cumulativeCountries_drop' name='cumulativeCountries_drop'>";
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> fixed = jdbc.getCountries();
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : fixed) {
            newList.add(data);
        }
        int i;
        for (i = 0; i < newList.size() - 1; i++) {
            cumulativevar = cumulativevar + " <option>" + newList.get(i);
        }
        cumulativevar = cumulativevar +"                  </option>";
        cumulativevar = cumulativevar +"              </select>";
        cumulativevar = cumulativevar +"            </div>";
        cumulativevar = cumulativevar +"            <div class='\"form-group\"'>";
        cumulativevar = cumulativevar +"                  <h4 class=\"textabovedays\">Enter the dates you would like the data collection to be inbetween:";
        cumulativevar = cumulativevar +"                  <input class='\"form-control1\"' id='date1_textbox' name='date1_textbox' type='date' value='2020-01-22' min='2020-01-22' max='2021-04-22'>";
        cumulativevar = cumulativevar +"                  <input class='\"form-control2\"' id='date2_textbox' name='date2_textbox' type='date' value='2021-04-22' min='2020-01-22' max='2021-04-22'>";
        cumulativevar = cumulativevar +"                  <h4 class=\"textabovedistance\">Enter the range around your country of choice to check data of countries within that range (in km):";
        cumulativevar = cumulativevar +"                  <input class='\"form-control3\"' id='distance_textbox' name='distance_textbox' type='number' min='0' step='1' placeholder='Enter integer here'>";
        cumulativevar = cumulativevar +"            </div>";
        cumulativevar = cumulativevar +"            <button type='submit' class='\"btn btn-shallow\"'>Go</button>";
        cumulativevar = cumulativevar +"        </div>";
        cumulativevar = cumulativevar +"   </form>";
        cumulativevar = cumulativevar +"    </section>";

        String date1_textbox = context.formParam("date1_textbox");
        String date2_textbox = context.formParam("date2_textbox");
        String distance_textbox = context.formParam("distance_textbox");
        String cumulativeCountries_drop = context.formParam("cumulativeCountries_drop");
        
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
            int distance = Integer.parseInt(distance_textbox);
            if (distance >= 0) {
                int a;
                int b;
                for (a = 0; a < totalDates.size(); a++) {
                    for (b = 0; b < totalDates.size(); b++) {
                        if (((firstDate.compareTo(totalDates.get(a)) == 0) && (secondDate.compareTo(totalDates.get(b)) == 0))) {
                            cumulativevar = cumulativevar + testOrder(cumulativeCountries_drop, firstDate, secondDate, distance);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            if (distance_textbox == "" || distance_textbox == null) {
                //cumulativevar = cumulativevar + doNothing(cumulativeCountries_drop);
            }
            if (distance_textbox != "" && distance_textbox != null) {
                //cumulativevar = cumulativevar + doNothing(cumulativeCountries_drop);
            }
        }
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(cumulativevar);
    }
    public String testOrder(String country, LocalDate date1, LocalDate date2, int distance) {
        String cumulativevar = "";
        if ((date2.compareTo(date1) < 0)) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            cumulativevar = cumulativevar + outputDate(country, secondDate, firstDate, distance);
        }
        else if (((date2.compareTo(date1) == 0) || (date2.compareTo(date1) > 0))) {
            String firstDate = date1.toString();
            String secondDate = date2.toString();
            cumulativevar = cumulativevar + outputDate(country, firstDate, secondDate, distance);
        }
        return cumulativevar;
    }
    public String outputDate(String country, String date1, String date2, int distance) {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2 class=\"similarclimate\">Similar Climate to " + country + " (" + date1 +" to " + date2 +")</h2>";
        
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> latitude = jdbc.getCountryLatitudeExtension(country);
        ArrayList<String> newLatitude = new ArrayList<String>();
        for (String data : latitude) {
            newLatitude.add(data);
        }
        String minLat = newLatitude.get(0);
        String maxLat = newLatitude.get(1);
        ArrayList<String> fixed = jdbc.getSimilarClimates(date1, date2, minLat, maxLat);
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : fixed) {
            newList.add(data);
        }
        int i;
        infectionsvar = infectionsvar + "<div class=\"tablediv\">";
        infectionsvar = infectionsvar + "<table class=\"transratetable\">";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Transmission Rate</th>";
        infectionsvar = infectionsvar + "     <th>Death Rate</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=3) {
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + '%' + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + '%' + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        infectionsvar = infectionsvar + "</div>";

        ArrayList<String> pointForCountryList = jdbc.getLatitudeAndLongitude(country);
        ArrayList<String> countryList = new ArrayList<String>();
        for (String newPoint : pointForCountryList) {
            countryList.add(newPoint);
        }
        String lati = countryList.get(0);
        String longi = countryList.get(1);
        ArrayList<String> distanceList = jdbc.getDistance(date1, date2, lati, longi, distance);
        ArrayList<String> omegaList = new ArrayList<String>();
        for (String alphaData: distanceList) {
            omegaList.add(alphaData);
        }
        infectionsvar = infectionsvar + "<div class=\"tablediv\">";
        infectionsvar = infectionsvar + "<table class=\"distancetable\">";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Infection Rate</th>";
        infectionsvar = infectionsvar + "     <th>Distance from POI</th>";
        infectionsvar = infectionsvar + " </tr>";
        int j;
        for (j = 0; j < omegaList.size() - 1; j+=3) {
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + omegaList.get(j) + "</td>";
            infectionsvar = infectionsvar + " <td>" + omegaList.get(j+1) + '%' + "</td>";
            infectionsvar = infectionsvar + " <td>" + omegaList.get(j+2) + " km" + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        infectionsvar = infectionsvar + "</div>";
        return infectionsvar;
    }
}