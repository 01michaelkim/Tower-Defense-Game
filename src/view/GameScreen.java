package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private int money = 500;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        FlowPane root = new FlowPane();
        Label label = new Label("Tower Defense");
        Image back = new Image("images//map.png");
        ImageView background = new ImageView(back);
        Label healthLabel = new Label("Health: " + health);
        Label moneyLabel = new Label("Money: " + money);
        VBox vbox = new VBox(healthLabel, moneyLabel);
        root.getChildren().addAll(label, background, vbox);
        Scene scene = new Scene(root, width, height);
        return scene;
    }
}
