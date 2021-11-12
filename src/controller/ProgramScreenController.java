package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import view.*;

abstract class ProgramScreenController implements ProgramScreenControllerInterface {
    protected Stage currentStage;
    protected Stage nextStage;
    protected Scene currentScene;
    private Player player;

    public ProgramScreenController() {
        this.player = null;
    }

    public Scene getScene() {
        return this.currentScene;
    }

    public Stage getStage() {
        return this.currentStage;
    }

    public void setNextStage(ProgramScreen programScreen) {
        if (programScreen instanceof WelcomeScreen) {
            WelcomeScreen welcomeScreen = (WelcomeScreen) programScreen;
            this.nextStage = programScreen.getStage();
        } else if (programScreen instanceof ConfigurationScreen) {
            ConfigurationScreen configurationScreen = (ConfigurationScreen) programScreen;
            this.nextStage = programScreen.getStage();
        } else if (programScreen instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) programScreen;
            this.nextStage = programScreen.getStage();
        } else if (programScreen instanceof GameOverScreen) {
            GameOverScreen gameOverScreen = (GameOverScreen) programScreen;
            this.nextStage = programScreen.getStage();
        }
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}