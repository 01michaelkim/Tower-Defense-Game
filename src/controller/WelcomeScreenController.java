package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.ConfigurationScreen;
import view.WelcomeScreen;

public class WelcomeScreenController extends ProgramScreenController {
    private Image startButtonDefault = new Image("images//startButton1.png");
    private Image startButtonHovered = new Image("images//startButton2.png");
    private ImageView startButton;

    public WelcomeScreenController(WelcomeScreen welcomeScreen) {
        this.currentStage = welcomeScreen.getStage();
        this.startButton = welcomeScreen.getStartButton();
    }

    public void startButtonHandler() {
        this.startButton.setOnMouseClicked(e -> {
            setNextStage(new ConfigurationScreen());
            currentStage.close();
            currentStage = null;
            currentStage = nextStage;
            currentStage.show();
        });

        this.startButton.setOnMouseEntered(e -> {
            this.startButton.setImage(this.startButtonHovered);
        });

        this.startButton.setOnMouseExited(e -> {
            this.startButton.setImage(this.startButtonDefault);
        });
    }
}
