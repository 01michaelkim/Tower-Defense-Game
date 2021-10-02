import controller.Controller;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

public class ControllerTest extends ApplicationTest {
    public void start(Stage primaryStage) throws Exception {
        Controller controller = new Controller();
        controller.start(primaryStage);
    }
}
