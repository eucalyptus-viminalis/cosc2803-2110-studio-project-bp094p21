package app;

import java.util.ArrayList;

import org.graalvm.compiler.nodes.gc.G1PostWriteBarrier;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class shallowglance implements Handler {
    
    public static final String URL = "/shallowglance.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String shallowglancevar = "<html lang=\"en\">";
        shallowglancevar = shallowglancevar +"    <meta charset=\"UTF-8\">"+
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
        "    <title>CovidWebsite</title>"+
        "    <link rel=\"stylesheet\" href=\"main.css\">";
        shallowglancevar = shallowglancevar +"</head>";
        shallowglancevar = shallowglancevar +"<body>";
        shallowglancevar = shallowglancevar +"    <div class=\"navbar\">";
        shallowglancevar = shallowglancevar +"        <img id=\"mobile-cta\" class=\"mobile-menu\" src=\"menu.svg\" alt=\"Open Navigation\">    ";
        shallowglancevar = shallowglancevar +"        <nav>";
        shallowglancevar = shallowglancevar +"            <img id=\"mobile-exit\" class=\"mobile-menu-exit\" src=\"exit.svg\" alt=\"Close Navigation\">";
        shallowglancevar = shallowglancevar +"            <ul>";
        shallowglancevar = shallowglancevar +"                <li><a href=\"bigpicture.html\">Big Picture</a></li>";
        shallowglancevar = shallowglancevar +"                <li><a href=\"shallowglance.html\" class=\"shallowglance\">Shallow Glance</a></li>";
        shallowglancevar = shallowglancevar +"                <li><a href=\"deepdive.html\">Deep Dive</a></li>";
        shallowglancevar = shallowglancevar +"            </ul>";
        shallowglancevar = shallowglancevar +"        </nav>";
        shallowglancevar = shallowglancevar +"    </div>";
        shallowglancevar = shallowglancevar +"    ";
        shallowglancevar = shallowglancevar +"   <form action='/shallowglance.html' method='post'>";
        shallowglancevar = shallowglancevar +"    <section class=\"shallowglancehero\">";
        shallowglancevar = shallowglancevar +"        <div class=\"shallowglanceleft-col\">";
        shallowglancevar = shallowglancevar +"            <h1 class=\"lefttext\">Please choose how you want to see the data with the settings below."; 
        shallowglancevar = shallowglancevar +"            <h4 class=\"notetext\">Note that dates not in the format YYYY-MM-DD, not between 2020-1-22 to 2021-4-22 inclusive, or are otherwise not correct/are blank, will result in default data. This will also occur if no selection is made in the dropdown box.</h1>";
        shallowglancevar = shallowglancevar +"            <div class='\"form-group\"'>";
        shallowglancevar = shallowglancevar +"              <select id='shalloworder_drop' name='shalloworder_drop'>";
        shallowglancevar = shallowglancevar +"                  <option></option>";
        shallowglancevar = shallowglancevar +"                  <option>Descending Order</option>";
        shallowglancevar = shallowglancevar +"                  <option>Ascending Order</option>";
        shallowglancevar = shallowglancevar +"            </div>";
        shallowglancevar = shallowglancevar +"            <div class='\"form-group\"'>";
        shallowglancevar = shallowglancevar +"                  <input class='\"form-control1\"' id='date1_textbox' name='date1_textbox' placeholder='YYYY-MM-DD'>";
        shallowglancevar = shallowglancevar +"                  <input class='\"form-control2\"' id='date2_textbox' name='date2_textbox' placeholder='YYYY-MM-DD'>";
        shallowglancevar = shallowglancevar +"            </div>";
        shallowglancevar = shallowglancevar +"            <button type='submit' class='\"btn btn-shallow\"'>Go</button>";
        shallowglancevar = shallowglancevar +"        </div>";
        shallowglancevar = shallowglancevar +"   </form>";
        shallowglancevar = shallowglancevar +"    </section>";

        String date1_textbox = context.formParam("date1_textbox");
        String date2_textbox = context.formParam("date2_textbox");
        String shalloworder_drop = context.formParam("shalloworder_drop");
        if (date1_textbox == null || date1_textbox == "" && date2_textbox == null || date2_textbox == "" && shalloworder_drop == null || shalloworder_drop == "") {
            shallowglancevar = shallowglancevar + doNothing();
        }
        else {
            shallowglancevar = shallowglancevar + outputDate1(date1_textbox);
        }
        shallowglancevar = shallowglancevar + "</body>" + "</html>";
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(shallowglancevar);
    }
    public String doNothing() {
        String shallowglancevar = "";
        shallowglancevar = shallowglancevar + "<h2>COVID-19 Data</h2>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDefaultData();
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        shallowglancevar = shallowglancevar + "<table style=width:100%>";
        shallowglancevar = shallowglancevar + " <tr>";
        shallowglancevar = shallowglancevar + "     <th>Country Name</th>";
        shallowglancevar = shallowglancevar + "     <th>Total Cases</th>";
        shallowglancevar = shallowglancevar + "     <th>Most Cases in a Day</th>";
        shallowglancevar = shallowglancevar + "     <th>Date of Most Cases</th>";
        shallowglancevar = shallowglancevar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=4) {
            shallowglancevar = shallowglancevar + "<tr>";
            shallowglancevar = shallowglancevar + " <td>" + newList.get(i) + "</td>";
            shallowglancevar = shallowglancevar + " <td>" + newList.get(i+1) + "</td>";
            shallowglancevar = shallowglancevar + " <td>" + newList.get(i+2) + "</td>";
            shallowglancevar = shallowglancevar + " <td>" + newList.get(i+3) + "</td>";
            shallowglancevar = shallowglancevar + "</tr>";
        }
        shallowglancevar = shallowglancevar + "</table>";
        return shallowglancevar;
    }

    public String outputDate1(String date1) {
        String shallowglancevar = "";
    }

    public String outputDate2(String date2) {

    }

    public String outputShallowOption(String option) {

    }

    public String outputCountries(String fin) {

    }
}