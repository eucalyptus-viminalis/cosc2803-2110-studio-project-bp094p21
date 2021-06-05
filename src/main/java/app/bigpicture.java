package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class bigpicture implements Handler {
    
    public static final String URL = "/bigpicture.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String html = "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
        "    <title>Big Picture</title>" +
        
        "    <meta charset='utf-8'>" +
        "    <meta name='viewport' content='width=device-width, initial-scale=1'>" +
        "    <meta name='author' content='@jinheock'>" +
        "    <meta name='description' content='a webpage by @jinheock'>" +
        "    <meta property='og:title' content='Big Picture'>" +
        "    <meta property='og:description' content='Big Picture'>" +
        "    <meta property='og:image' content='/some-image.png'>" +
        "    <meta property='og:url' content='/this-page.html'>" +
        "    <meta property='og:site_name' content='Big Picture'>" +
        "    <meta name='twitter:card' content='summary_large_image'>" +
        "    <meta name='twitter:image:alt' content='image description'>" +
        
        "    <link href='reset.css' rel='stylesheet'>" +
        "    <link href='style.css' rel='stylesheet'>" +
        "    <link rel='icon' type='image/svg+xml' href='covid.svg'>" +
        "</head>" +
        "<body>" +
        "<nav class='nav-topnav'>" +
        "   <ul class='ul-topnav'>" +
        "       <a href='bigpicture.html' class='a-topnav a-topnav-highlight'>Big Picture" +
        "       </a>" +
        "       <a href='infections.html' class='a-topnav'>Infections" +
        "       </a>" +
        "       <a href='deaths.html' class='a-topnav'>Deaths" +
        "       </a>" +
        "       <a href='cumulative.html' class='a-topnav'>Cumulative" +
        "       </a>" +
        "       <a href='similar.html' class='a-topnav no-right-border'>Similar" +
        "       </a>" +
        "   </ul>" +
        "</nav>" +
        "<main>" +
        "    <h1>Covid-19</h1>" +
        "    <div class='div-globe'>" +
        "       <img src='spinning-globe.gif'>" +
        "    </div>" +
        "    <div class='div-facts'>";
        JDBCConnection jdbc = new JDBCConnection();
        int totalCases = jdbc.getPastYearCasesCountryTotal() + jdbc.getPastYearCasesStateTotal();
        html = html +
        "    <p class='p-fact'>In the past year, the world population has been infected with <span class='span-highlight'>" +
        totalCases/1000000 + " Million</span> new Covid-19 cases.</p>";
        String countryName = jdbc.getPastMonthDeathsCountryTop1Name();
        int countrySum = jdbc.getPastMonthDeathsCountryTop1Sum();
        int totalSum = jdbc.getPastMonthDeathsCountryTotal();
        html = html +
        "    <p class='p-fact'><span class='span-highlight'>" +
        countryName + "</span> leads the world in the total number of Covid-19 related deaths reported in the past month, accounting for " + (countrySum*100)/totalSum +
        "% of the month's global death count.*</p>";
        ArrayList<String> countryNames = jdbc.getPastWeekNoCasesCountry();
        int count = jdbc.getPastWeekNoCasesStateCount();
        String strCountryNames = "";
        for (String name : countryNames) {
            strCountryNames = strCountryNames + name + ", ";
        }
        html = html +
        "    <p class='p-fact'>" +
        strCountryNames + " along with <span class='span-hightlight'>" + count + " states and provinces around the world, have reported <span class='span-highlight'>0 new infections</span> in the past week.^</p>" +
        "    <div class='div-small'><small>*: excludes regional/provincial data</small>" +
        "    <small>^: week ranging from 2021-04-16 to 2021-04-22, inclusive</small></div>";
        html = html +
        "    </div>" +
        "</main>" +
        "    <script src='litty.js'></script>" +
        "</body>" +
        "</html>";
            
        
            
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}