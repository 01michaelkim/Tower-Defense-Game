package view;

import entities.Tower1;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        // Set a border pane
        BorderPane border = new BorderPane();

        // Create the Title of the Game Screen and add to the top of border pane
        // MIGHT REMOVE???
        HBox title = new HBox();
        Label label = new Label("College Student Tower Defense");
        title.getChildren().add(label);
        title.setAlignment(Pos.CENTER);
        border.setTop(title);

        // Set the background image and add to the center of the border pane

        Image backImage = new Image("images//map.png");
        ImageView background = new ImageView(backImage);
        border.setCenter(background);

        // Set player stats and add to the bottom of the border pane
        VBox playerStats = new VBox();
        Label healthLabel = new Label("Health: " + health);
        Label moneyLabel = new Label("Money: " + money);
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);
        playerStats.getChildren().addAll(healthLabel, moneyLabel, nameLabel);
        playerStats.setAlignment(Pos.CENTER_LEFT);
        border.setBottom(playerStats);


        // Create Tower Menu
        VBox towerShop = new VBox();
        Tower1 firstTower = new Tower1();
        ImageView firstTowerImage = firstTower.getImageView();
        firstTowerImage.setFitHeight(50);
        firstTowerImage.setFitWidth(50);
        towerShop.getChildren().add(firstTowerImage);
        border.setRight(towerShop);

        Scene scene = new Scene(border, width, height);

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
