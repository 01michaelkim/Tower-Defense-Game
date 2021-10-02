package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ConfigurationScreen {
    private int width;
    private int height;
    private Button playButton;

    public ConfigurationScreen(int width, int height) {
        this.width = width;
        this.height = height;
        playButton = new Button("Play Game");
    }

    public Scene getScene() {
        Label label = new Label("Configurations");
        HBox button = new HBox(playButton);
        VBox vbox = new VBox(label, button);
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public Button getStartButton() {
        return playButton;
    }
}
