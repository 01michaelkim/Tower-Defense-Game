import controller.Controller;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.GameModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.robot.Motion;
import org.testfx.service.query.NodeQuery;

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
        //System.out.println()
        clickOn(controller.getStart());
        //Enter Name
        clickOn(controller.getNameLabel()).write("M3 Player");
        clickOn(controller.getDropdown());
    }
    private void getToGameScreen() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
    }
    /**
     moveTo(1240, 200, Motion.DEFAULT);
     press(MouseButton.PRIMARY);
     moveTo(865, 350);
     release(MouseButton.PRIMARY);
     Origin of Map on Gerardo's Screen is roughly (715, 175)
     */
    @Test
    public void plantTowerOnPath() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        drag(1240,200, MouseButton.PRIMARY).dropTo(1035, 225);
        verifyThat(controller.getPlaced(),NodeMatchers.isNull());
    }
    @Test
    public void plantTowerNotOnPath() {
        getToGameScreen();
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 350);
        verifyThat(controller.getPlaced(),NodeMatchers.isNotNull());
    }
    @Test
    public void notebookTowerOnPath() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        drag(1240,250, MouseButton.PRIMARY).dropTo(1035, 415);
        verifyThat(controller.getPlaced(),NodeMatchers.isNull());
    }
    @Test
    public void notebookTowerNotOnPath() {
        getToGameScreen();
        drag(1240,250, MouseButton.PRIMARY).dropTo(1000, 495);
        verifyThat(controller.getPlaced(),NodeMatchers.isNotNull());
    }
    @Test
    public void fishTowerOnPath() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        drag(1240,300, MouseButton.PRIMARY).dropTo(795, 400);
        verifyThat(controller.getPlaced(),NodeMatchers.isNull());
    }
    @Test
    public void fishTowerNotOnPath() {
        getToGameScreen();

        drag(1240,300, MouseButton.PRIMARY).dropTo(865, 350);
        verifyThat(controller.getPlaced(),NodeMatchers.isNotNull());
    }
    @Test
    public void moneyTestEasy() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        //drag(525, 25, MouseButton.PRIMARY).dropTo(150, 150);
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 350);
        Assert.assertEquals(450, GameModel.getMoney());
    }
    @Test
    public void moneyTestMedium() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        //drag(525, 25, MouseButton.PRIMARY).dropTo(150, 150);
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 350);
        Assert.assertEquals(400, GameModel.getMoney());
    }
    @Test
    public void moneyTestHard() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        //drag(525, 25, MouseButton.PRIMARY).dropTo(150, 150);
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 350);
        Assert.assertEquals(350, GameModel.getMoney());
    }
    @Test
    public void noMoneyTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        //drag(525, 25, MouseButton.PRIMARY).dropTo(150, 150);
        drag(1240,200, MouseButton.PRIMARY).dropTo(820, 350);
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 350);
        drag(1240,200, MouseButton.PRIMARY).dropTo(865, 320);
        drag(1240,200, MouseButton.PRIMARY).dropTo(840, 310);
        Assert.assertEquals(50, GameModel.getMoney());

    }
}
