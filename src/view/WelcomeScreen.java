package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomeScreen {
    private int width;
    private int height;
    private Image startButtonDefault = new Image("images//startButton1.png");
    private Image startButtonHovered = new Image("images//startButton2.png");
    private ImageView startButton;

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.startButton = new ImageView(startButtonDefault);
    }

    public Scene getScene() {
        Label label = new Label("Welcome to Tower Defense!");
        HBox button = new HBox(startButton);
        button.setAlignment(Pos.TOP_CENTER);
        VBox vbox = new VBox(label, button);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(200);
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }


    public ImageView getStartButton() {
        return startButton;
    }

    public void toggleStartButton() {
        if (startButton.getImage().equals(startButtonDefault)) {
            startButton.setImage(startButtonHovered);
        } else {
            startButton.setImage(startButtonDefault);
        }
    }
}
