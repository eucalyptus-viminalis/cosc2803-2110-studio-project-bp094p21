package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples.
 * This page currently:
 *  - Provides a link back to the index page
 *  - Displays the list of movies from the Movies Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class deaths implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/deaths.html";
    public static final String[] DATES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Override
    public void handle(Context context) throws Exception {
        String country;
        String from_date;
        String to_date;
        if (context.formParam("country") != null) {
            country = context.formParam("country");
        } else {
            country = "Australia";
        };
        if (context.formParam("from-date") != null && context.formParam("to-date") != null) {
            if ((context.formParam("from-date").compareTo(context.formParam("to-date"))) < 0) {
                from_date = context.formParam("from-date");
                to_date = context.formParam("to-date");
            } else{
                from_date = context.formParam("to-date");
                to_date = context.formParam("from-date");
            }
        } else {
            from_date = "2020-01-22";
            to_date = "2021-04-22";
        }
        JDBC2             jdbc = new JDBC2();
        int               country_population = jdbc.getCountryPopulation(country);
        long               global_population = jdbc.getGlobalPopulation();
        ArrayList<String> country_names = jdbc.getCountryNames();
        ArrayList<String> peak_data = jdbc.getPeakData(country);
        int               country_deaths = jdbc.getTotalDeathsInDateRange1Country(country, from_date, to_date);
        int               country_total_deaths = jdbc.getTotalDeathsInDateRange1Country(country, "2020-01-22", "2021-04-22");
        int               global_total_deaths = jdbc.getGlobalTotalDeaths();
        System.out.println(country_deaths);
        int               country_cases = jdbc.getTotalCasesInDateRange1Country(country, from_date, to_date);
        // int               country_total_cases = jdbc.getTotalCasesInDateRange1Country(country, "2020-01-22", "2021-04-22");
        int country_ratio;
        if (country_cases == 0) {
            country_ratio = 0;
        } else {
            country_ratio = (100 * country_deaths) / country_cases;
        }
        String peak_date = peak_data.get(0);
        String peak_deaths = peak_data.get(1);
        String str_yyyy = peak_date.substring(0,4);
        String str_mm = peak_date.substring(5,7);
        String str_dd = peak_date.substring(8, 10);
        int m = Integer.parseInt(str_mm);
        String str_month = DATES[m - 1];
        if (str_dd.charAt(0) == '0') {
            str_dd = str_dd.substring(1,2);
        }
        // int num_countries = country_names.size();
        int i;
        ArrayList<String> global_data = jdbc.getGlobalData();
        int global_rows = global_data.size()/3;
        System.out.println(global_rows);
        String html = "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
        "    <title>Deaths ☠️</title>" +
        
        "    <meta charset='utf-8'>" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1'>" +
        "    <meta name='author' content='@jinheock'>" +
        "    <meta name='description' content='a webpage by @jinheock'>" +
        "    <meta property='og:title' content='Deaths ☠️'>" +
        "    <meta property='og:description' content='Deaths ☠️'>" +
        "    <meta property='og:image' content='/some-image.png'>" +
        "    <meta property='og:url' content='/this-page.html'>" +
        "    <meta property='og:site_name' content='Deaths ☠️'>" +
        "    <meta name='twitter:card' content='summary_large_image'>" +
        "    <meta name='twitter:image:alt' content='image description'>" +
        
        "    <link href='reset.css' rel='stylesheet'>" +
        "    <link href='style.css' rel='stylesheet'>" +
        "    <link rel='icon' type='image/svg+xml' href='covid.svg'>" +
        "</head>" +
        "<body>" +
        "<nav class='nav-topnav'>" +
        "   <ul class='ul-topnav'>" +
        "       <li class='li-nav'><a href='bigpicture.html' class='a-topnav'>Big Picture" +
        "       </a></li>" +
        "       <li class='li-nav'><a href='infections.html' class='a-topnav'>Infections" +
        "       </a></li>" +
        "       <li class='li-nav li-nav-highlight'><a href='deaths.html' class='a-topnav a-topnav-highlight'>Deaths" +
        "       </a></li>" +
        "       <li class='li-nav'><a href='cumulative.html' class='a-topnav'>Cumulative" +
        "       </li></a>" +
        "       <li class='li-nav'><a href='similar.html' class='a-topnav no-right-border'>Similar" +
        "       </a></li>" +
        "   </ul>" +
        "</nav>" +
        "<main>" +
        "    <h1>Covid-19</h1>" +
        "    <section class='section-deaths'>" +
        "       <div class='div-halfwidth'>" +
        "           <h3 class='h3-country'>" + country + "</h3>" +
        "           <p class='p-peak'>On <span class='span-highlight'>" + str_month + " " + str_dd + ", " + str_yyyy + "</span><br><span class='span-highlight'>" + country + "</span> had the highest number of <br>deaths in one day, totalling <span class='span-highlight'>" + peak_deaths +"</span> deaths.</p>" +
        "           <form id='formCountry' action='/deaths.html' method='post'>" +
        "               <fieldset>" +
        "               <legend>Date Range:</legend>" +
        "               <select class='select-css' id='country' name='country' onChange='this.form.submit()'>";
        // "               <option value='' selected='' disabled=''>Choose Country</option>";
        
        for (String name : country_names) {
            if (name.equals(country)) {
                html = html + "<option selected value='" + name + "'>" + name + "</option>";
            } else {
                html = html + "<option value='" + name + "'>" + name + "</option>";
            }
        }
        html = html +
        "               </select>" +
        "               <div class='div-dates'>" +
        "               <input class='input-date input-from-date' id='from-date' name='from-date' type='date' min='2020-01-22' max='2021-04-22' onchange='this.form.submit()' value='" + from_date + "'>" +
        "               <input class='input-date input-to-date' id='to-date' name='to-date' type='date' min='2020-01-22' max='2021-04-22' onchange='this.form.submit()' value='" + to_date + "'></div>" +
        "           </form>" +
        "           <section class='section-death-ratio'>" +
        "           <div class='div-death'><h3><span class='span-highlight2'>Deaths</span></h3>" +
        "           <h4>" + country_deaths + "</h4></div>" +
        "           <div class='div-ratio'><h3><span class='span-highlight2'>Ratio*</span></h3>" +
        "           <h4>" + country_ratio + "%</h4></div>" +
        "           </section>" +
        "           </fieldset>" + 
        "           <h3>Total Deaths</h3>" +
        "           <h4>" + country_total_deaths + "</h4>" +
        "           <h3>Population</h3>" +
        "           <h4>" + country_population + "</h4>" +
        "       </div>" +
        "       <div class='div-halfwidth'>" +
        "       <h3 class='h3-global'>Global</h3>" +
        "       <div class='div-scroll'>" +
        "       <table id='table-global'>" +
        "       <thead class='thead-deaths'>" +
        "           <tr>" +
        "               <th class='btn-th' data-type='string'>Country</th>" +
        "               <th class='btn-th' data-type='num'>Deaths</th>" +
        "               <th class='btn-th' data-type='num'>Ratio* (%)</th>" +
        "           </tr>" +
        "       </thead>" +
        "       <tbody>";
        for (i = 0; i < global_rows; i++) {
            html = html +
            "<tr>" +
            "<td>" + global_data.get(i*3) + "</td>" +
            "<td>" + global_data.get((i*3)+1) + "</td>" +
            "<td>" + global_data.get((i*3)+2) + "</td>" +
            "</tr>";
        }
        html = html +
        "       </tbody>" +
        "       </table></div>" +
        "           <h3>Total Deaths</h3>" +
        "           <h4>" + global_total_deaths + "</h4>" +
        "           <h3>Population</h3>" +
        "           <h4>" + global_population/1000000000 + "," + global_population%1000000000/1000000 + "," + global_population%1000000/1000 + "," + global_population%1000 + "</h4>" +
        "       </div>" +
        "       </div>" +
        "    </section>" +
        "       <div class='div-line'></div>" +
        "       <div class='div-ratio-note'>* deaths to infections</div>" +
        "        <a class='a-home' href='/'><img class='img-home' src='covid.svg'></a>" +
        "</main>" +
        "    <script src='litty.js'></script>" +
        "</body>" +
        "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
