import controller.Controller;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.GameModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;
public class M3ControllerTest extends ApplicationTest {
    private Controller controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.start(primaryStage);
    }

    @Before
    public void setup() throws Exception {
    }
    private void getToConfig() {
        clickOn(controller.getStart());
        //Enter Name
        clickOn(controller.getNameLabel()).write("M2 Player");
        clickOn(controller.getDropdown());
    }

    @Test
    public void dragTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        drag(525, 25, MouseButton.PRIMARY).dropTo(150, 150);
    }

}
