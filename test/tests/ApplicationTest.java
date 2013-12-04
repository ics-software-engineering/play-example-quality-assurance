package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.*;
import play.mvc.*;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import static org.fest.assertions.Assertions.assertThat;
import play.mvc.Content;
import static play.test.Helpers.contentType;
import static play.test.Helpers.contentAsString;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }


}
