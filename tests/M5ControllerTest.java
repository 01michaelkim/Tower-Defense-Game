import application.Main;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import view.*;

import static org.testfx.api.FxAssert.verifyThat;

public class M5ControllerTest extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }
    @Before
    public void setup() throws Exception {
    }
    private void getToConfig() {
        //System.out.println()
        clickOn(((WelcomeScreen) controller.getScreen()).getStartButton());
        clickOn(((ConfigurationScreen) controller.getScreen()).getNameLabel()).write("M5 Player");
        clickOn(((ConfigurationScreen) controller.getScreen()).getDropdown());
    }
    private void getToGameScreen() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
    }
    private void getCombatStart() {
        getToGameScreen();
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
    }
    /*
    private void getToGameOver() {
        getToGameScreen();
        clickOn(controller.getStart());
        try {
            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    */
    @Test
    public void easyHealthCheck() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        getCombatStart();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(250, Player.getHealth());
    }
    @Test
    public void medHealthCheck() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        getCombatStart();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(100, Player.getHealth());
    }
    @Test
    public void hardHealthCheck() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        getCombatStart();
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //check gameover.
    }
    @Test
    public void moneyTestEasy() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 350);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        //drag then wait
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(850, Player.getMoney());
    }
    @Test
    public void moneyTestMedium() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 350);
        //precision not clear
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 260);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1200, Player.getMoney());
    }
    @Test
    public void moneyTestHard() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 350);
        //precision not clear
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 260);
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 290);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(2050, Player.getMoney());
    }
    @Test
    public void enemyDeadTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        drag(1205, 200, MouseButton.PRIMARY).dropTo(865, 350);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean allDead = true;
        for (int i = 0; i < ((GameScreen) controller.getScreen()).getEnemyList().size(); i++) {
            if (((GameScreen) controller.getScreen()).getEnemyList().get(i).getisAlive()) {
                allDead = false;
            }
        }
        Assert.assertTrue(allDead);
    }
    @Test
    public void enemyAliveTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        //drag plant
        drag(1205, 200, MouseButton.PRIMARY).dropTo(865, 350);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean allDead = true;
        for (int i = 0; i < ((GameScreen) controller.getScreen()).getEnemyList().size(); i++) {
            if (((GameScreen) controller.getScreen()).getEnemyList().get(i).getisAlive()) {
                allDead = false;
            }
        }
        Assert.assertTrue(!allDead);
    }
    @Test
    public void gameOverTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verifyThat(((GameOverScreen) controller.getScreen()).getRestartButton(),
                NodeMatchers.isNotNull());
        verifyThat(((GameOverScreen) controller.getScreen()).getExitButton(),
                NodeMatchers.isNotNull());
    }
    @Test
    public void notGameOverTest() {
        getToConfig();
        press(KeyCode.DOWN).release(KeyCode.DOWN);
        press(KeyCode.ENTER).release(KeyCode.ENTER);
        clickOn(((ConfigurationScreen) controller.getScreen()).getPlayButton());
        //drag fish
        drag(1205, 280, MouseButton.PRIMARY).dropTo(865, 350);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verifyThat(((GameOverScreen) controller.getScreen()).getRestartButton(),
                NodeMatchers.isNull());
        verifyThat(((GameOverScreen) controller.getScreen()).getExitButton(),
                NodeMatchers.isNull());
    }
}

