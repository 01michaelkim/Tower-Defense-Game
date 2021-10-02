package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameScreen {
    private int width;
    private int height;
    private Button startButton;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        Label label = new Label("Tower Defense");
        Scene scene = new Scene(label, width, height);
        return scene;
    }
}
