package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomeScreen {
    private int width;
    private int height;
    private Button startButton;

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        startButton = new Button("Start Game");
    }

    public Scene getScene() {
        Label label = new Label("Welcome to Tower Defense!");
        HBox button = new HBox(startButton);
        VBox vbox = new VBox(label, button);
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public Button getStartButton() {
        return startButton;
    }
}
