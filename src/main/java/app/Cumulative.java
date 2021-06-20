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
        cumulativevar = cumulativevar +"    <link rel=\"stylesheet\" href=\"style.css\">";
        cumulativevar = cumulativevar +"    <link rel=\"stylesheet\" href=\"main.css\">";
        cumulativevar = cumulativevar +"</head>";
        cumulativevar = cumulativevar +"<body>";
        cumulativevar = cumulativevar +"<nav class='nav-topnav'>" ;
        cumulativevar = cumulativevar +"   <ul class='ul-topnav'>" ;
        cumulativevar = cumulativevar +"       <li class='li-nav'><a href='bigpicture.html' class='a-topnav'>Big Picture" ;
        cumulativevar = cumulativevar +"       </a></li>" ;
        cumulativevar = cumulativevar +"       <li class='li-nav'><a href='infections.html' class='a-topnav'>Infections" ;
        cumulativevar = cumulativevar +"       </a></li>" ;
        cumulativevar = cumulativevar +"       <li class='li-nav'><a href='deaths.html' class='a-topnav'>Deaths" ;
        cumulativevar = cumulativevar +"       </a></li>" ;
        cumulativevar = cumulativevar +"       <li class='li-nav li-nav-highlight no-right-border'><a href='cumulative.html' class='a-topnav a-topnav-highlight'>Cumulative" ;
        cumulativevar = cumulativevar +"       </li></a>" ;
        cumulativevar = cumulativevar +"       <li class='li-nav'><a href='similar.html' class='a-topnav'>Similar" ;
        cumulativevar = cumulativevar +"       </a></li>" ;
        cumulativevar = cumulativevar +"   </ul>" ;
        cumulativevar = cumulativevar +"</nav>" ;
        cumulativevar = cumulativevar +"    </div>";
        cumulativevar = cumulativevar +"   <form action='/cumulative.html' method='post'>";
        cumulativevar = cumulativevar +"    <section class=\"cumulativehero\">";
        cumulativevar = cumulativevar +"        <div class=\"cumulativeleft-col\">";
        cumulativevar = cumulativevar +"            <h1 class=\"lefttext\">Welcome to the Cumulative Page!";
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">On this page you will be presented with tables of data that display in depth information on COVID-19 cases and deaths based on country."; 
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">You will also be able to make adjustments to the dates the data is presented between, and how far from a country a comparison is made. Scroll down to have a look!";
        cumulativevar = cumulativevar +"            <h4 class=\"datetext\">Please also be aware that if at least one of the date boxes are left empty, the table presented will default to the dates that cover the whole range of data collection (2020-01-22 to 2021-04-22), and if the distance box is left empty, the value will default to 0.";
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
        cumulativevar = cumulativevar +
        "        <a class='a-home' href='/'><img class='img-home' src='covid.svg'></a>";

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
            if (cumulativeCountries_drop == null) {
                cumulativeCountries_drop = "Afghanistan";
                LocalDate firstDate = LocalDate.parse(sta);
                LocalDate secondDate = LocalDate.parse(en);
                int distance = 0;
                cumulativevar = cumulativevar + testOrder(cumulativeCountries_drop, firstDate, secondDate, distance);
            }
            else if ((distance_textbox == "" || distance_textbox == null) && (date1_textbox == "" || date2_textbox == "")) {
                LocalDate firstDate = LocalDate.parse(sta);
                LocalDate secondDate = LocalDate.parse(en);
                int distance = 0;
                cumulativevar = cumulativevar + testOrder(cumulativeCountries_drop, firstDate, secondDate, distance);
            }
            else if ((distance_textbox == "" || distance_textbox == null)) {
                LocalDate firstDate = LocalDate.parse(date1_textbox);
                LocalDate secondDate = LocalDate.parse(date2_textbox);
                int distance = 0;
                cumulativevar = cumulativevar + testOrder(cumulativeCountries_drop, firstDate, secondDate, distance);
            }
            else if ((distance_textbox != "" && distance_textbox != null) && (date1_textbox == "" || date2_textbox == "")) {
                LocalDate firstDate = LocalDate.parse(sta);
                LocalDate secondDate = LocalDate.parse(en);
                int distance = Integer.parseInt(distance_textbox);
                cumulativevar = cumulativevar + testOrder(cumulativeCountries_drop, firstDate, secondDate, distance);
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
        String cumulativevar = "";
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Similar Climate to " + country + " (" + date1 +" to " + date2 +")</h2>";
        
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
        cumulativevar = cumulativevar + "<div class=\"tablediv\">";
        cumulativevar = cumulativevar + "<table class=\"transratetable\">";
        cumulativevar = cumulativevar + "<thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Transmission Rate</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Death Rate</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        for (i = 0; i < newList.size() - 1; i+=3) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + newList.get(i) + "</td>";
            cumulativevar = cumulativevar + " <td>" + newList.get(i+1) + '%' + "</td>";
            cumulativevar = cumulativevar + " <td>" + newList.get(i+2) + '%' + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";

        ArrayList<String> pointForCountryList = jdbc.getLatitudeAndLongitude(country);
        ArrayList<String> countryList = new ArrayList<String>();
        for (String newPoint : pointForCountryList) {
            countryList.add(newPoint);
        }
        String lati = countryList.get(0);
        String longi = countryList.get(1);
        ArrayList<String> distanceList = jdbc.getDistance(date1, date2, lati, longi, distance, country);
        ArrayList<String> omegaList = new ArrayList<String>();
        for (String alphaData: distanceList) {
            omegaList.add(alphaData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Distance from " + country + " by " + distance + "km (" + date1 +" to " + date2 +")</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv\">";
        cumulativevar = cumulativevar + "<table class=\"distancetable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='num'>Transmission Rate</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='num'>Distance from POI</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int j;
        for (j = 0; j < omegaList.size() - 1; j+=3) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + omegaList.get(j) + "</td>";
            cumulativevar = cumulativevar + " <td>" + omegaList.get(j+1) + '%' + "</td>";
            cumulativevar = cumulativevar + " <td>" + omegaList.get(j+2) + " km" + "</td>";
            cumulativevar = cumulativevar + "</tr></tbody>";
        }
        cumulativevar = cumulativevar + "</table>";
        cumulativevar = cumulativevar + "</div>";

        ArrayList<String> infDeaRatio = jdbc.getInfDeaRatio(date1, date2, country);
        ArrayList<String> litList = new ArrayList<String>();
        for (String regData: infDeaRatio) {
            litList.add(regData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Ratios of " + country + " (" + date1 +" to " + date2 +")</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv3\">";
        cumulativevar = cumulativevar + "<table class=\"infdeafratiotable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Infection : Death Ratio</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Infection : Population Ratio (per 10000 people)</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Death : Population Ratio (per 10000 people)</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int o;
        for (o = 0; o <litList.size() - 1; o+=4) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + litList.get(o) + "</td>";
            cumulativevar = cumulativevar + " <td>" + litList.get(o+1) + " : 1" + "</td>";
            cumulativevar = cumulativevar + " <td>" + litList.get(o+2) + " : 10000" + "</td>";
            cumulativevar = cumulativevar + " <td>" + litList.get(o+3) + " : 10000" + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";
        cumulativevar = cumulativevar + "<script src='litty.js'></script>";

        ArrayList<String> leastRecovered = jdbc.getLeastRecovered();
        ArrayList<String> ehlist = new ArrayList<String>();
        for (String neatData: leastRecovered) {
            ehlist.add(neatData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Country that has recovered the worst in the last 7 days</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv3\">";
        cumulativevar = cumulativevar + "<table class=\"infdeafratiotable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Number of Cases</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Number of Deaths</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int t;
        for (t = 0; t <ehlist.size() - 1; t+=3) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + ehlist.get(t) + "</td>";
            cumulativevar = cumulativevar + " <td>" + ehlist.get(t+1) + "</td>";
            cumulativevar = cumulativevar + " <td>" + ehlist.get(t+2) + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";
        cumulativevar = cumulativevar + "<script src='litty.js'></script>";

        ArrayList<String> mostRecovered = jdbc.getMostRecovered();
        ArrayList<String> coollist = new ArrayList<String>();
        for (String noiceData: mostRecovered) {
            coollist.add(noiceData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Country that has recovered the best in the last 7 days</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv3\">";
        cumulativevar = cumulativevar + "<table class=\"infdeafratiotable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Number of Cases</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Number of Deaths</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int u;
        for (u = 0; u <coollist.size() - 1; u+=3) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + coollist.get(u) + "</td>";
            cumulativevar = cumulativevar + " <td>" + coollist.get(u+1) + "</td>";
            cumulativevar = cumulativevar + " <td>" + coollist.get(u+2) + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";
        cumulativevar = cumulativevar + "<script src='litty.js'></script>";

        ArrayList<String> highRates = jdbc.getHighRates();
        ArrayList<String> epicList = new ArrayList<String>();
        for (String epicData: highRates) {
            epicList.add(epicData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Top 5 Countries with Highest Infections Ratio per 1 million people over entire history</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv2\">";
        cumulativevar = cumulativevar + "<table class=\"infdeafratiotable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Infections</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int v;
        for (v = 0; v <epicList.size() - 1; v+=2) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + epicList.get(v) + "</td>";
            cumulativevar = cumulativevar + " <td>" + epicList.get(v+1) + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";
        cumulativevar = cumulativevar + "<script src='litty.js'></script>";

        ArrayList<String> lowRates = jdbc.getLowRates();
        ArrayList<String> sickList = new ArrayList<String>();
        for (String sickData: lowRates) {
            sickList.add(sickData);
        }
        cumulativevar = cumulativevar + "<h2 class=\"cumulativemsg\">Top 5 Countries with Highest Death Ratio per 1 million people over entire history</h2>";
        cumulativevar = cumulativevar + "<div class=\"tablediv2\">";
        cumulativevar = cumulativevar + "<table class=\"infdeafratiotable\"><thead>";
        cumulativevar = cumulativevar + " <tr>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Country Name</th>";
        cumulativevar = cumulativevar + "     <th class='btn-th' data-type='string'>Deaths</th>";
        cumulativevar = cumulativevar + " </tr></thead><tbody>";
        int y;
        for (y = 0; y <sickList.size() - 1; y+=2) {
            cumulativevar = cumulativevar + "<tr>";
            cumulativevar = cumulativevar + " <td>" + sickList.get(y) + "</td>";
            cumulativevar = cumulativevar + " <td>" + sickList.get(y+1) + "</td>";
            cumulativevar = cumulativevar + "</tr>";
        }
        cumulativevar = cumulativevar + "</tbody></table>";
        cumulativevar = cumulativevar + "</div>";
        cumulativevar = cumulativevar + "<script src='litty.js'></script>";
        return cumulativevar;
    }
}