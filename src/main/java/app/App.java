package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class App {

    public static final int         JAVALIN_PORT    = 7000;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));
            config.addStaticFiles(CSS_DIR);
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        app.get(Index.URL, new Index());
        app.get(BigPicture.URL, new BigPicture());
        app.get(Infections.URL, new Infections());
        app.get(Cumulative.URL, new Cumulative());
        app.get(Deaths.URL, new Deaths());
        app.get(Similar.URL, new Similar());

        // Add / uncomment POST commands for any pages that need web form POSTS
        app.post(Infections.URL, new Infections());
        // app.post(Page1.URL, new Page1());
        // app.post(Page2.URL, new Page2());
        // app.post(Page3.URL, new Page3());
        // app.post(Page4.URL, new Page4());
        // app.post(Page5.URL, new Page5());
        // app.post(Page6.URL, new Page6());
    }

}
