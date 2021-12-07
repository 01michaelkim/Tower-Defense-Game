import application.Main;
import entities.Tower;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.GameModel;
import model.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import view.*;

import static org.testfx.api.FxAssert.verifyThat;

public class M6ControllerTest extends ApplicationTest {
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
        clickOn(((ConfigurationScreen) controller.getScreen()).getNameLabel()).write("M6 Player");
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
    private void getToWinScreen() {
        getToGameScreen();
        drag(1210, 248, MouseButton.PRIMARY).dropTo(805, 300);
        drag(1210, 248, MouseButton.PRIMARY).dropTo(865, 300);

        drag(1210, 280, MouseButton.PRIMARY).dropTo(865, 350);
        drag(1210, 280, MouseButton.PRIMARY).dropTo(1000, 300);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drag(1210, 280, MouseButton.PRIMARY).dropTo(890, 470);
        drag(1210, 280, MouseButton.PRIMARY).dropTo(990, 470);
        drag(1210, 280, MouseButton.PRIMARY).dropTo(930, 470);

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void checkGameOver() {
        getToGameScreen();
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
    public void checkBossKillsYou() {
        getToGameScreen();
        drag(1210, 248, MouseButton.PRIMARY).dropTo(805, 300);
        drag(1210, 248, MouseButton.PRIMARY).dropTo(865, 300);

        drag(1210, 280, MouseButton.PRIMARY).dropTo(865, 350);
        //drag(1210, 280, MouseButton.PRIMARY).dropTo(1000, 300);
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drag(1210, 280, MouseButton.PRIMARY).dropTo(890, 470);
        drag(1210, 280, MouseButton.PRIMARY).dropTo(990, 470);
        drag(1210, 280, MouseButton.PRIMARY).dropTo(930, 470);

        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verifyThat("Kills: 20", NodeMatchers.isNotNull());
    }
    @Test
    public void winScreenExists() {
        getToWinScreen();
        verifyThat("Kills: 21", NodeMatchers.isNotNull());
    }
    @Test
    public void checkWinStats() {
        getToWinScreen();
        verifyThat("Player Health: 300", NodeMatchers.isNotNull());
        verifyThat("Player Money: 1050", NodeMatchers.isNotNull());
        verifyThat("Kills: 21", NodeMatchers.isNotNull());
    }
    @Test
    public void checkLoseStats() {
        getToGameScreen();
        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verifyThat("Player Health: 0", NodeMatchers.isNotNull());
        verifyThat("Player Money: 500", NodeMatchers.isNotNull());
        verifyThat("Kills: 0", NodeMatchers.isNotNull());
    }
    @Test
    public void checkWinRestart() {
        getToWinScreen();
        clickOn(((WinScreen) controller.getScreen()).getRestartButton());
        verifyThat("Welcome to Tower Defense!", NodeMatchers.isNotNull());
    }
    @Test
    public void checkWinExit() {
        getToWinScreen();
        clickOn(((WinScreen) controller.getScreen()).getExitButton());
        Assert.assertTrue(GameModel.getGameClosed());
    }
    @Test
    public void checkPlantUpgrade() {
        getToGameScreen();
        drag(1210, 248, MouseButton.PRIMARY).dropTo(805, 300);
        drag(1210, 248, MouseButton.PRIMARY).dropTo(865, 300);

        drag(1210, 280, MouseButton.PRIMARY).dropTo(865, 350);
        drag(1210, 200, MouseButton.PRIMARY).dropTo(920, 320);

        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(((GameScreen)controller.getScreen()).getLeftButton());
        clickOn(((GameScreen)controller.getScreen()).getUpgradeButton());
        System.out.println(((GameScreen)controller.getScreen()).isTowerUpgraded());
        Assert.assertTrue(((GameScreen)controller.getScreen()).isTowerUpgraded());
    }
    @Test
    public void checkNoteBookUpgrade() {
        getToGameScreen();
        drag(1210, 248, MouseButton.PRIMARY).dropTo(805, 300);
        drag(1210, 248, MouseButton.PRIMARY).dropTo(865, 300);

        drag(1210, 280, MouseButton.PRIMARY).dropTo(865, 350);
        drag(1210, 200, MouseButton.PRIMARY).dropTo(920, 320);

        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(((GameScreen)controller.getScreen()).getUpgradeButton());
        System.out.println(((GameScreen)controller.getScreen()).isTowerUpgraded());
        Assert.assertTrue(((GameScreen)controller.getScreen()).isTowerUpgraded());
    }
    @Test
    public void checkFishUpgrade() {
        getToGameScreen();
        drag(1210, 248, MouseButton.PRIMARY).dropTo(805, 300);
        drag(1210, 248, MouseButton.PRIMARY).dropTo(865, 300);

        drag(1210, 280, MouseButton.PRIMARY).dropTo(865, 350);

        clickOn(((GameScreen) controller.getScreen()).getStartButton());
        try {
            Thread.sleep(13000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickOn(((GameScreen)controller.getScreen()).getLeftButton());
        clickOn(((GameScreen)controller.getScreen()).getUpgradeButton());
        System.out.println(((GameScreen)controller.getScreen()).isTowerUpgraded());
        Assert.assertTrue(((GameScreen)controller.getScreen()).isTowerUpgraded());
    }
}
