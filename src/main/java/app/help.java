package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class help implements Handler {
    
    public static final String URL = "/help.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String bigpicturevar = "<html lang=\"en\">"+
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
"                <li><a href=\"shallowglance.html\">Shallow Glance</a></li>"+
"                <li><a href=\"deepdive.html\">Deep Dive</a></li>"+
"                <li><a href=\"help.html\" class=\"help\">Help</a></li>"+
"            </ul>"+
"        </nav>"+
"    </div>"+
"</body>"+
"</html>";
            
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(bigpicturevar);
    }

}