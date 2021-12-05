package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;
import view.WinScreen;
import view.WelcomeScreen;

public class WinScreenController extends ProgramScreenController {
    private Image restartButtonDefault = new Image("images//restartButton1.png");
    private Image restartButtonHovered = new Image("images//restartButton2.png");
    private ImageView restartButton;
    private Image exitButtonDefault = new Image("images//exitButton1.png");
    private Image exitButtonHovered = new Image("images//exitButton2.png");
    private ImageView exitButton;

    public WinScreenController(WinScreen winScreen) {
        this.restartButton = winScreen.getRestartButton();
        this.exitButton = winScreen.getExitButton();
        this.currentStage = winScreen.getStage();
    }
    public void startButtonHandlers() {
        this.restartButtonHandler();
        this.exitButtonHandler();
    }

    private void restartButtonHandler() {
        this.restartButton.setOnMouseClicked(e -> {
            setNextStage(new WelcomeScreen());
            currentStage.close();
            currentStage = null;
            currentStage = nextStage;
            currentStage.show();
        });

        this.restartButton.setOnMouseEntered(e -> {
            this.restartButton.setImage(this.restartButtonHovered);
        });

        this.restartButton.setOnMouseExited(e -> {
            this.restartButton.setImage(this.restartButtonDefault);
        });
    }

    private void exitButtonHandler() {
        this.exitButton.setOnMouseClicked(e -> {
            GameModel.setGameClosed();
            currentStage.close();
        });

        this.exitButton.setOnMouseEntered(e -> {
            this.exitButton.setImage(this.exitButtonHovered);
        });

        this.exitButton.setOnMouseExited(e -> {
            this.exitButton.setImage(this.exitButtonDefault);
        });
    }
}