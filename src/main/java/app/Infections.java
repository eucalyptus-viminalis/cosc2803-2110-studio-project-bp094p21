package app;

import java.util.ArrayList;

import org.graalvm.compiler.nodes.gc.G1PostWriteBarrier;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class infections implements Handler {
    
    public static final String URL = "/infections.html";
    
    @Override
    public void handle(Context context) throws Exception {
        String infectionsvar = "<html lang=\"en\">";
        infectionsvar = infectionsvar +"    <meta charset=\"UTF-8\">"+
        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"+
        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"+
        "    <title>CovidWebsite</title>"+
        "    <link rel=\"stylesheet\" href=\"main.css\">";
        infectionsvar = infectionsvar +"</head>";
        infectionsvar = infectionsvar +"<body>";
        infectionsvar = infectionsvar +"    <div class=\"navbar\">";
        infectionsvar = infectionsvar +"        <img id=\"mobile-cta\" class=\"mobile-menu\" src=\"menu.svg\" alt=\"Open Navigation\">    ";
        infectionsvar = infectionsvar +"        <nav>";
        infectionsvar = infectionsvar +"            <img id=\"mobile-exit\" class=\"mobile-menu-exit\" src=\"exit.svg\" alt=\"Close Navigation\">" +
        "            <ul class='nav-ul'>"+
        "                <li class='btn-lvl1'><a href=\"bigpicture.html\">Big Picture</a></li>"+
        "                <li class='btn-lvl2 highlight-red'><a href=\"infections.html\">Infections</a></li>"+
        "                <li class='btn-lvl2'><a href=\"deaths.html\">Deaths</a></li>"+
        "                <li class='btn-lvl3'><a href=\"cumulative.html\">Cumulative</a></li>"+
        "                <li class='btn-lvl3'><a href=\"similar.html\">Similar</a></li>"+
        "            </ul>";
        infectionsvar = infectionsvar +"        </nav>";
        infectionsvar = infectionsvar +"    </div>";
        infectionsvar = infectionsvar +"    ";
        infectionsvar = infectionsvar +"   <form action='/infections.html' method='post'>";
        infectionsvar = infectionsvar +"    <section class=\"shallowglancehero\">";
        infectionsvar = infectionsvar +"        <div class=\"shallowglanceleft-col\">";
        infectionsvar = infectionsvar +"            <h1 class=\"lefttext\">Please choose how you want to see the data with the settings below."; 
        infectionsvar = infectionsvar +"            <h4 class=\"notetext\">Note that dates not in the format YYYY-MM-DD, not between 2020-1-22 to 2021-4-22 inclusive, or are otherwise not correct/are blank, will result in default data. This will also occur if no selection is made in the dropdown box.</h1>";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"              <select id='shalloworder_drop' name='shalloworder_drop'>";
        infectionsvar = infectionsvar +"                  <option></option>";
        infectionsvar = infectionsvar +"                  <option>Descending Order</option>";
        infectionsvar = infectionsvar +"                  <option>Ascending Order</option>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <div class='\"form-group\"'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control1\"' id='date1_textbox' name='date1_textbox' placeholder='YYYY-MM-DD'>";
        infectionsvar = infectionsvar +"                  <input class='\"form-control2\"' id='date2_textbox' name='date2_textbox' placeholder='YYYY-MM-DD'>";
        infectionsvar = infectionsvar +"            </div>";
        infectionsvar = infectionsvar +"            <button type='submit' class='\"btn btn-shallow\"'>Go</button>";
        infectionsvar = infectionsvar +"        </div>";
        infectionsvar = infectionsvar +"   </form>";
        infectionsvar = infectionsvar +"    </section>";

        String date1_textbox = context.formParam("date1_textbox");
        String date2_textbox = context.formParam("date2_textbox");
        String shalloworder_drop = context.formParam("shalloworder_drop");
        if (((date1_textbox == null || date1_textbox == "") || (date2_textbox == null || date2_textbox == "")) && (shalloworder_drop == null || shalloworder_drop == "")) {
            infectionsvar = infectionsvar + doNothing();
        }
        else {
            infectionsvar = infectionsvar + outputDate1(date1_textbox);
        }
        infectionsvar = infectionsvar + "</body>" + "</html>";
// DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(infectionsvar);
    }
    public String doNothing() {
        String infectionsvar = "";
        infectionsvar = infectionsvar + "<h2>COVID-19 Data</h2>";

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> covid = jdbc.getDefaultData();
        ArrayList<String> newList = new ArrayList<String>();
        for (String data : covid) {
            newList.add(data);
        }
        int i;
        infectionsvar = infectionsvar + "<table style=width:100%>";
        infectionsvar = infectionsvar + " <tr>";
        infectionsvar = infectionsvar + "     <th>Country Name</th>";
        infectionsvar = infectionsvar + "     <th>Total Cases</th>";
        infectionsvar = infectionsvar + "     <th>Most Cases in a Day</th>";
        infectionsvar = infectionsvar + "     <th>Date of Most Cases</th>";
        infectionsvar = infectionsvar + " </tr>";
        for (i = 0; i < newList.size() - 1; i+=4) {
            infectionsvar = infectionsvar + "<tr>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+1) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+2) + "</td>";
            infectionsvar = infectionsvar + " <td>" + newList.get(i+3) + "</td>";
            infectionsvar = infectionsvar + "</tr>";
        }
        infectionsvar = infectionsvar + "</table>";
        return infectionsvar;
    }

    public String outputDate1(String date1) {
        String infectionsvar = "";
    }

    public String outputDate2(String date2) {

    }

    public String outputShallowOption(String option) {

    }

    public String outputCountries(String fin) {

    }
}