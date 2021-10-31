import controller.Controller;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import model.GameModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.api.FxAssert.verifyThat;
public class M4ControllerTest extends ApplicationTest {
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
        clickOn(controller.getNameLabel()).write("M4 Player");
        clickOn(controller.getDropdown());
    }
    private void getToGameScreen() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
    }
    private void getToGameOver() {
        getToGameScreen();
        clickOn(controller.getStart());
        try {

            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void gameOverTest() {
        getToGameOver();
        verifyThat(controller.getRestart(), NodeMatchers.isNotNull());
        verifyThat(controller.getExit(), NodeMatchers.isNotNull());
    }
    @Test
    public void restartRestarts() {
        getToGameOver();
        clickOn(controller.getRestart());
        verifyThat("Welcome to Tower Defense!", NodeMatchers.isNotNull());
    }
    @Test
    public void checkDeadHealth() {
        getToGameOver();
        Assert.assertTrue(GameModel.getHealth() <= 0);
    }
    @Test
    public void checkEasyHealthReset() {
        getToGameOver();
        clickOn(controller.getRestart());
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Health: 300", NodeMatchers.isNotNull());
    }

    @Test
    public void checkMedHealthReset() {
        getToGameOver();
        clickOn(controller.getRestart());
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Health: 200", NodeMatchers.isNotNull());
    }

    @Test
    public void checkHardHealthReset() {
        getToGameOver();
        clickOn(controller.getRestart());
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Health: 100", NodeMatchers.isNotNull());
    }
    @Test
    public void checkMoneyReset() {
        getToGameOver();
        clickOn(controller.getRestart());
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Money: 500", NodeMatchers.isNotNull());
    }
    @Test
    public void testExit() {
        getToGameOver();
        clickOn(controller.getExit());
        Assert.assertTrue(GameModel.getGameClosed());
    }
    @Test
    public void testButtonVisibility() {
        getToGameScreen();
        clickOn(controller.getStart());
        try {

            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(false, controller.getStart().isVisible());
    }
    @Test
    public void testButtonExistence() {
        getToGameScreen();
        verifyThat(controller.getStart(), NodeMatchers.isNotNull());
    }
}
