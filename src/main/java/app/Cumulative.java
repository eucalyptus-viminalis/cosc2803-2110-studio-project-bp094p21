package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class cumulative implements Handler {
    
    public static final String URL = "/cumulative.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String cumulativevar = "<html lang=\"en\">"+
        "<head>"+
        "    <meta charset=\"UTF-8\">"+
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
        "    <title>CovidWebsite</title>"+
        "    <link rel=\"stylesheet\" href=\"main.css\">"+
        "</head>"+
        "<body>"+
        "    <div class=\"navbar\">"+
        "        <img id=\"mobile-cta\" class=\"mobile-menu\" src=\"menu.svg\" alt=\"Open Navigation\">"+
        "        <nav>"+
        "            <img id=\"mobile-exit\" class=\"mobile-menu-exit\" src=\"exit.svg\" alt=\"Close Navigation\">"+
        "            <ul class='nav-ul'>"+
        "                <li class='btn-lvl1'><a href=\"bigpicture.html\">Big Picture</a></li>"+
        "                <li class='btn-lvl2'><a href=\"infections.html\">Infections</a></li>"+
        "                <li class='btn-lvl2'><a href=\"deaths.html\">Deaths</a></li>"+
        "                <li class='btn-lvl3 highlight-red'><a href=\"cumulative.html\">Cumulative</a></li>"+
        "                <li class='btn-lvl3'><a href=\"similar.html\">Similar</a></li>"+
        "            </ul>"+
        "        </nav>"+
        "    </div>"+
        "    <script>"+
        "        const mobileBtn = document.getElementById('mobile-cta')"+
        "            nav = document.querySelector('nav');"+
        "            mobileBtnExit = document.getElementById('mobile-exit');"+
        ""+
        "        mobileBtn.addEventListener('click', () => {"+
        "            nav.classList.add('menu-btn');"+
        "        })"+
        ""+
        "        mobileBtnExit.addEventListener('click', () => {"+
        "            nav.classList.remove('menu-btn');"+
        "        })"+
        "    </script>"+
        "</body>"+
        "</html>";
            
        
            
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(cumulativevar);
    }

}