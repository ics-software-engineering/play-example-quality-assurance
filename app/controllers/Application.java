package controllers;

import play.*;
import play.mvc.*;
import views.html.*;
import java.util.List;  // To trigger a PMD warning.

public class Application extends Controller {
    private Object uselessField = null;  // To trigger a FindBugs warning.
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    

}
