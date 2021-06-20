package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class similar implements Handler {
    
    public static final String URL = "/similar.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String countryState;
        String varName;
        String varTh = "Country";
        if (context.formParam("countryState") != null) {
            countryState = context.formParam("countryState");
        } else {
            countryState = "country";
        };
        if (context.formParam("list") != null) {
            varName = context.formParam("list");
        } else {
            varName = "Australia";
        };
        if (countryState.equals("state")) {
            varTh = "State";
        } else {
            varTh = "Country";
        }
        JDBC2             jdbc = new JDBC2();
        ArrayList<String> country_names = jdbc.getCountryNames();
        ArrayList<String> us_state_names = jdbc.getUSStateNames();
        ArrayList<String> similar_casespermil;
        ArrayList<String> banner_list_items;
        ArrayList<String> similar_ratio;
        ArrayList<String> similar_peak_deaths;
        ArrayList<String> similar_peak_cases;
        ArrayList<String> list;
        if (countryState.equals("country")) {
            list = country_names;
            similar_casespermil = jdbc.get3SimilarCasesCountryMil(varName);
            similar_ratio = jdbc.get3SimilarRatioCountry(varName);
            similar_peak_deaths = jdbc.get3SimilarPeakDeathsCountry(varName);
            similar_peak_cases = jdbc.get3SimilarPeakCasesCountry(varName);
            banner_list_items = country_names;
        } else {
            list = us_state_names;
            similar_casespermil = jdbc.get3SimilarCasesUSStateMil(varName);
            similar_ratio = jdbc.get3SimilarRatioUSState(varName);
            similar_peak_deaths = jdbc.get3SimilarPeakDeathsUSState(varName);
            similar_peak_cases = jdbc.get3SimilarPeakCasesUSState(varName);
            banner_list_items = us_state_names;
        }
        int i;
        System.out.println(similar_ratio);
        System.out.println(similar_peak_cases);
        System.out.println(similar_peak_deaths);
        // int               totalCases = jdbc.getPastYearTotalCasesCountry();
        // String            countryName = jdbc.getPastMonthDeathsCountryTop1Name();
        // int               countrySum = jdbc.getPastMonthDeathsCountryTop1Sum();
        // int               totalSum = jdbc.getPastMonthDeathsCountryTotal();
        // ArrayList<String> countryNames = jdbc.getPastWeekNoCasesCountry();
        // int               count = jdbc.getPastWeekNoCasesStateCount();
        // String            strCountryNames = "";
        // for (String name : countryNames) {
        //     strCountryNames = strCountryNames + name + ", ";
        // }

        String html = "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
        "    <title>Similar ❶❷❸</title>" +
        
        "    <meta charset='utf-8'>" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1'>" +
        "    <meta name='author' content='@jinheock'>" +
        "    <meta name='description' content='a webpage by @jinheock'>" +
        "    <meta property='og:title' content='Similar ❶❷❸'>" +
        "    <meta property='og:description' content='Similar ❶❷❸'>" +
        "    <meta property='og:image' content='/some-image.png'>" +
        "    <meta property='og:url' content='/this-page.html'>" +
        "    <meta property='og:site_name' content='Similar ❶❷❸'>" +
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
        "       <li class='li-nav'><a href='deaths.html' class='a-topnav'>Deaths" +
        "       </a></li>" +
        "       <li class='li-nav'><a href='cumulative.html' class='a-topnav'>Cumulative" +
        "       </li></a>" +
        "       <li class='li-nav li-nav-highlight no-right-border'><a href='similar.html' class='a-topnav a-topnav-highlight'>Similar" +
        "       </a></li>" +
        "   </ul>" +
        "</nav>" +
        "<main>" +
        "    <h1>Covid-19</h1>" +
        "    <form id='formSimilar' action='/similar.html' method='post'>" +
        "               <label for='countryState' class='label-1'>Find Similar: </label>" +
        "               <select class='select-css' id='countryState' name='countryState' onChange='this.form.submit()'>" +
        "               <option value='country' ";
        if (countryState.equals("country")) {
            html = html + "selected>Countries</option>";
        } else {
            html = html + ">Countries</option>";
        }
        html = html +
        "               <option value='state' ";
        if (countryState.equals("state")) {
            html = html + "selected>U.S. States</option>";
        } else {
            html = html + ">U.S. States</option>";
        }
        html = html +
        "               </select> " +
        "               <label for='list' class='label-2'>To: </label>" +
        "               <select class='select-css' id='list' name='list' onChange='this.form.submit()'>";
        // "               <option value='' selected='' disabled=''>Choose Country</option>";
        
        for (String name : list) {
            if (name.equals(varName)) {
                html = html + "<option selected value='" + name + "'>" + name + "</option>";
            } else {
                html = html + "<option value='" + name + "'>" + name + "</option>";
            }
        }
        html = html +
        "               </select>" +
        "           </form>" +
        "           <div class='div-tables'>" +
        "       <table>" +
        "       <thead>" +
        "           <tr>" +
        "               <th data-type='string'>" + varTh + "</th>" +
        "               <th data-type='string'>Infections<br><small>per million ppl</small></th>" +
        "           </tr>" +
        "       </thead>" +
        "       <tbody>";
        for (i = 0; i < 4; i++) {
            html = html +
            "<tr>" +
            "<td>" + similar_casespermil.get(i*2) + "</td>" +
            "<td>" + similar_casespermil.get((i*2)+1) + "</td>" +
            "</tr>";
        }
        html = html +
        "       </tbody></table>" +
        "       <table>" +
        "       <thead>" +
        "           <tr>" +
        "               <th data-type='string'>" + varTh + "</th>" +
        "               <th data-type='num'>Ratio<br><small>deaths/infection</small></th>" +
        "           </tr>" +
        "       </thead>" +
        "       <tbody>";
        for (i = 0; i < 4; i++) {
            html = html +
            "<tr>" +
            "<td>" + similar_ratio.get(i*2) + "</td>" +
            "<td>" + similar_ratio.get((i*2)+1) + "%</td>" +
            "</tr>";
        }
        html = html +
        "       </tbody></table>" +
        "       <table>" +
        "       <thead>" +
        "           <tr>" +
        "               <th data-type='string'>" + varTh + "</th>" +
        "               <th data-type='num'>Peak Deaths<br><small>highest number of new<br>deaths in 1 day</small></th>" +
        "           </tr>" +
        "       </thead>" +
        "       <tbody>";
        for (i = 0; i < 4; i++) {
            html = html +
            "<tr>" +
            "<td>" + similar_peak_deaths.get(i*3) + "</td>" +
            "<td>" + similar_peak_deaths.get((i*3)+2) + "<br><small>" + similar_peak_deaths.get((i*3)+1) + "</small></td>" +
            "</tr>";
        }
        html = html +
        "       </tbody></table>" +
        "       <table>" +
        "       <thead>" +
        "           <tr>" +
        "               <th data-type='string'>" + varTh + "</th>" +
        "               <th data-type='num'>Peak Cases<br><small>highest number of new<br>cases in 1 day</small></th>" +
        "           </tr>" +
        "       </thead>" +
        "       <tbody>";
        for (i = 0; i < 4; i++) {
            html = html +
            "<tr>" +
            "<td>" + similar_peak_cases.get(i*3) + "</td>" +
            "<td>" + similar_peak_cases.get((i*3)+2) + "<br><small>" + similar_peak_deaths.get((i*3)+1) + "</small></td>" +
            "</tr>";
        }
        html = html +
        "       </tbody></table></div>" +
        "    <div class='horizontal-scrolling-banner'>";

        for (String name : banner_list_items) {
            html = html + "<div class='div-banner-item'>" + name + "</div>";
        }
        html = html +
        "    </div>" +
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