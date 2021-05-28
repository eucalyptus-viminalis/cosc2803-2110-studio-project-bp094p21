package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class shallowglance implements Handler {
    
    public static final String URL = "/shallowglance.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String shallowglancevar = "<html lang=\"en\">"+
"<head>"+
"    <meta charset=\"UTF-8\">"+
"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
"    <title>CovidWebsite</title>"+
"    <link rel=\"stylesheet\" href=\"main.css\">"+
"</head>"+
"<body>"+
"    <div class=\"navbar\">"+
"        <nav>"+
"            <ul>"+
"                <li><a href=\"bigpicture.html\">Big Picture</a></li>"+
"                <li><a href=\"shallowglance.html\" class=\"shallowglance\">Shallow Glance</a></li>"+
"                <li><a href=\"deepdive.html\">Deep Dive</a></li>"+
"                <li><a href=\"help.html\">Help</a></li>"+
"            </ul>"+
"        </nav>"+
"    </div>"+
"    "+
"    <section class=\"shallowglancehero\">"+
"        <div class=\"shallowglanceleft-col\">"+
"            <h1 class=\"lefttext\">Ranked from worst infected by COVID-19 in:</h1>"+
"            <h4 class=\"descorder\">Descending Order</h4>"+
"            <a href=\"#\" class=\"desc-cta\">Click here</a>"+
"            <h4 class=\"ascorder\">Ascending Order</h4>"+
"            <a href=\"#\" class=\"asc-cta\">Click here</a>"+
"        </div>"+
"        <div class=\"shallowglanceright-col\">"+
"            <h1 class=\"righttext\">Choose a period to look at:</h1>"+
""+
"        </div>"+
"    </section>"+
"</body>"+
"</html>";
            
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(shallowglancevar);
    }

}