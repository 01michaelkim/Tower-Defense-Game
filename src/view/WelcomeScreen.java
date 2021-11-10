package view;

import controller.WelcomeScreenController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomeScreen extends ProgramScreen {
    private final int width = 500;
    private final int height = 500;
    private WelcomeScreenController controller;

    private ImageView startButton;

    public WelcomeScreen() {
        this.initStage(this.width, this.height);
        this.initStageElements();
        this.initController();
    }

    @Override
    public void initController() {
        this.controller = new WelcomeScreenController(this);
        this.startController();
    }

    public void startController() {
        this.currentStage.show();
        this.controller.startButtonHandler();
    }

    public void initStageElements() {
        this.createStartButton();
        this.createStage();
    }

    public void createStartButton() {
        this.startButton = new ImageView(new Image("images//startButton1.png"));
    }

    public void createStage() {
        Label label = new Label("Welcome to Tower Defense!");

        HBox button = new HBox(startButton);
        button.setAlignment(Pos.TOP_CENTER);
        VBox vbox = new VBox(label, button);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(200);

        this.pane.setCenter(vbox);
    }

    public ImageView getStartButton() {
        return startButton;
    }
}
