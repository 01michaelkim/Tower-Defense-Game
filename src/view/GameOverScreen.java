package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GameOverScreen {
    private int width;
    private int height;
    private Image restartButtonDefault = new Image("images//restartButton1.png");
    private Image restartButtonHovered = new Image("images//restartButton2.png");
    private ImageView restartButton;
    private Image exitButtonDefault = new Image("images//exitButton1.png");
    private Image exitButtonHovered = new Image("images//exitButton2.png");
    private ImageView exitButton;


    public GameOverScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.restartButton = new ImageView(restartButtonDefault);
        this.exitButton = new ImageView(exitButtonDefault);
    }

    public Scene getScene() {
        Image goImage = new Image("images//gameOver.png");
        ImageView gameOver = new ImageView(goImage);
        HBox left = new HBox(restartButton);
        left.setAlignment(Pos.CENTER_LEFT);
        HBox right = new HBox(exitButton);
        right.setAlignment(Pos.CENTER_RIGHT);
        HBox button = new HBox(left, right);
        button.setAlignment(Pos.TOP_CENTER);
        button.setSpacing(50);
        VBox vbox = new VBox(gameOver, button);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(200);
        Scene scene = new Scene(vbox, width, height);

        // Set the Style Sheet for the Scene
        scene.getStylesheets().add("resources/SceneStyle.css");
        return scene;
    }


    public ImageView getRestartButton() {
        return restartButton;
    }
    public ImageView getExitButton() {
        return exitButton;
    }

    public void toggleRestartButton() {
        if (restartButton.getImage().equals(restartButtonDefault)) {
            restartButton.setImage(restartButtonHovered);
        } else {
            restartButton.setImage(restartButtonDefault);
        }
    }
    public void toggleExitButton() {
        if (exitButton.getImage().equals(exitButtonDefault)) {
            exitButton.setImage(exitButtonHovered);
        } else {
            exitButton.setImage(exitButtonDefault);
        }
    }
}
