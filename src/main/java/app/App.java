package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {

    public static final int         JAVALIN_PORT    = 7000;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";
    public static final String      JS_DIR          = "js/";

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            config.addStaticFiles(CSS_DIR);
            config.addStaticFiles(IMAGES_DIR);
            config.addStaticFiles(JS_DIR);
        }).start(JAVALIN_PORT);
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        app.get(index.URL, new index());
        app.get(bigpicture.URL, new bigpicture());
        app.get(infections.URL, new infections());
        app.get(cumulative.URL, new cumulative());
        app.get(deaths.URL, new deaths());
        app.get(similar.URL, new similar());

        // Add / uncomment POST commands for any pages that need web form POSTS
        app.post(infections.URL, new infections());
        app.post(deaths.URL, new deaths());
        // app.post(Page2.URL, new Page2());
        // app.post(Page3.URL, new Page3());
        // app.post(Page4.URL, new Page4());
        // app.post(Page5.URL, new Page5());
        // app.post(Page6.URL, new Page6());
    }

}
