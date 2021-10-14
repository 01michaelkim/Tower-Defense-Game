package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private int money = 500;
    private String characterName;
    private ToolBar towerMenu;

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
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);

        // Create Tower Menu
//        towerMenu = new ToolBar();


        // Create Rectangle
//        Rectangle selectionRectangle = new Rectangle();
//        selectionRectangle.setStroke(Color.BLACK);
//        selectionRectangle.setFill(Color.RED);

        VBox vbox = new VBox(healthLabel, moneyLabel, nameLabel);
        root.getChildren().addAll(label, background, vbox);
        Scene scene = new Scene(root, width, height);

        // Set the Style Sheet for the Scene
        scene.getStylesheets().add("resources/SceneStyle.css");
        return scene;
    }
    public void checkMoney(String s) {
        if (s.equals("EASY")) {
            health = 300;
        } else if (s.equals("MEDIUM")) {
            health = 200;
        } else if (s.equals("HARD")) {
            health = 100;
        }
    }

}
