package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomeScreen {
    private int width;
    private int height;
    //changed type to imageView
    Image strtBtn1 = new Image("images//startButton1.png");
    Image strtBtn2 = new Image("images//startButton2.png");
    private ImageView startButton;

    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        //startButton = new Button("Start Game");

        //Start Button 1
        startButton = new ImageView(strtBtn1);
    }

    public Scene getScene() {
        Label label = new Label("Welcome to Tower Defense!");
        HBox button = new HBox(startButton);
        button.setPadding(new Insets(200, 5, 5, 20));
        //VBox vbox = new VBox(label, button);
        VBox vbox = new VBox(label, button);
        vbox.setPadding(new Insets(15, 5, 5, 170));
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }
    //switched return type from Button to ImageView
    public ImageView getStartButton() {
        return startButton;
    }
    public void toggleStartButton() {
        if (startButton.getImage().equals(strtBtn1)) {
            startButton.setImage(strtBtn2);
        } else {
            startButton.setImage(strtBtn1);
        }
    }
}
