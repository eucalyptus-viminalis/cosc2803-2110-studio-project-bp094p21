package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class index implements Handler {

    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        String homepagevar = "<html lang=\"en\">"+
    "<head>"+
    "    <meta charset=\"UTF-8\">"+
    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
    "    <title>CovidWebsite</title>"+
    "    <link rel=\"stylesheet\" href=\"style.css\">"+
    "    <link rel=\"stylesheet\" href=\"main.css\">"+
    "</head>"+
    "<body>"+
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
        "       <li class='li-nav'><a href='similar.html' class='a-topnav'>Similar" +
        "       </a></li>" +
        "   </ul>" +
        "</nav>" +
    ""+
    "    <section class=\"homepagehero\" alt=\"COVID-19 Picture\">"+
    "        <img src=\"covid.svg\">"+
    "        <h1>Stay alert. Say NO to COVID-19.</h1>"+
    "        <h4>All the data you need in one place.</h4>"+
    "        <a href=\"bigpicture.html\" class=\"primary-cta\">SAVE LIVES</a>"+
    "    </section>"+
    "</body>"+
    "</html>";
            
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(homepagevar);
    }

}
