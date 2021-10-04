import controller.Controller;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.testfx.api.FxAssert.verifyThat;

public class ControllerTest extends ApplicationTest {
    private Controller controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        controller.start(primaryStage);
    }
    @Before
    public void setup() throws Exception {

    }

    @Test
    public void checkStart() {
        clickOn(controller.getStart());
        //checks that you are at Configuration Screen
        verifyThat("Set Game Configurations", NodeMatchers.isNotNull());
    }
    private void getToConfig() {
        clickOn(controller.getStart());
        //Enter Name
        clickOn(controller.getNameLabel()).write("M2 Player");
        clickOn(controller.getDropdown());
    }
    @Test
    public void checkPlay() {
        clickOn(controller.getStart());
        //Enter Name
        clickOn(controller.getNameLabel()).write("M2 Player");
        clickOn(controller.getDropdown());
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        //Press Play
        clickOn(controller.getPlayButton());
        verifyThat("Tower Defense", NodeMatchers.isNotNull());
    }
    @Test
    public void checkEasy() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        Assert.assertEquals(controller.getGameModel().getDifficulty(), "EASY");
    }
    @Test
    public void checkMedium() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        Assert.assertEquals(controller.getGameModel().getDifficulty(), "MEDIUM");
    }
    @Test
    public void checkHard() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        Assert.assertEquals(controller.getGameModel().getDifficulty(), "HARD");
    }
    @Test
    public void checkNoDiff() {
        clickOn(controller.getStart());
        clickOn(controller.getNameLabel()).write("M2 Player");
        clickOn(controller.getPlayButton());
        verifyThat("Please Select a Difficulty", NodeMatchers.isNotNull());
    }
    @Test
    public void checkNoName() {
        clickOn(controller.getStart());
        clickOn(controller.getDropdown());
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("You MUST enter a name that isn't empty!", NodeMatchers.isNotNull());
    }
    @Test
    public void checkEasyHealth() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Health: 300", NodeMatchers.isNotNull());
    }
    @Test
    public void checkMedHealth() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Health: 200", NodeMatchers.isNotNull());
    }
    @Test
    public void checkHardHealth() {
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
    public void checkMoney() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Money: 500", NodeMatchers.isNotNull());
    }
    @Test
    public void checkName() {
        getToConfig();
        //Choose Difficulty
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(controller.getPlayButton());
        verifyThat("Name: M2 Player", NodeMatchers.isNotNull());
    }
}
