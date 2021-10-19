package view;

//import entities.Tower1;
import entities.Fish;
import entities.Notebook;
import entities.Plant;
import entities.Tower;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import model.GameModel;


public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private String characterName;
    private Label moneyLabel;
    private ToolBar towerMenu;
    private Tower plant;
    private Tower notebook;
    private Tower fish;
    private Pane cent;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        plant = new Plant();
        notebook = new Notebook();
        fish = new Fish();
        cent = new Pane();
    }

    public Scene getScene() {
        // Set a border pane
        BorderPane border = new BorderPane();

        // Set the background image and add to the center of the border pane
        Image map = new Image("images//map2.png");
        BackgroundImage backImage = new BackgroundImage(map,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backImage);
        cent.setBackground(background);
        border.setCenter(cent);

        // Set player stats and add to the bottom of the border pane
        VBox playerStats = new VBox();
        Label healthLabel = new Label("Health: " + health);
        moneyLabel = new Label("Money: " + GameModel.getMoney());
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);
        playerStats.getChildren().addAll(healthLabel, moneyLabel, nameLabel);
        playerStats.setAlignment(Pos.CENTER_LEFT);
        border.setBottom(playerStats);


        // Create Tower Menu
        VBox towerShop = new VBox();

        // Plant
        ImageView plantTower = plant.getImageView();

        // Notebook
        ImageView notebookTower = notebook.getImageView();

        // Fish
        ImageView fishTower = fish.getImageView();

        towerShop.getChildren().addAll(plantTower, notebookTower, fishTower);
        border.setRight(towerShop);

        //dragDrop(plant, cent);
        //dragDrop(notebook, cent);
        //dragDrop(fish, cent);

        Tooltip plantToolTip = new Tooltip(plant.getDescription());
        Tooltip.install(plantTower, plantToolTip);

        Tooltip notebookToolTip = new Tooltip(notebook.getDescription());
        Tooltip.install(notebookTower, notebookToolTip);

        Tooltip fishToolTip = new Tooltip(fish.getDescription());
        Tooltip.install(fishTower, fishToolTip);

        Scene scene = new Scene(border, width, height);

        // Set the Style Sheet for the Scene
        scene.getStylesheets().add("resources/SceneStyle.css");
        return scene;
    }


    public void checkDifficulty(String s) {
        if (s.equals("EASY")) {
            health = 300;
        } else if (s.equals("MEDIUM")) {
            health = 200;
        } else if (s.equals("HARD")) {
            health = 100;
        }
    }

    /**
     * Checks if mouse is on path.
     * @param x mouse x coordinate.
     * @param y mouse y coordinate.
     * @return true or false.
     */
    public boolean isPath(double x, double y) {
        boolean flag = false;
        if (y <= 80) {
            if (x < 400) {
                flag = true;
            }
        } else if (y <= 200) {
            if (x >= 320 && x < 400) {
                flag = true;
            }
        } else if (y <= 280) {
            if (x < 400) {
                flag = true;
            }
        } else if (y <= 360) {
            if (x < 80) {
                flag = true;
            }
        } else if (y <= 440) {
            flag = true;
        } else if (y > 440) {
            flag = false;
        } else {
            flag = false;
        }
        return flag;
    }
    public Tower getTower(int i) {
        if (i == 1) {
            return plant;
        } else if (i == 2) {
            return notebook;
        } else if (i == 3) {
            return fish;
        } else {
            return null;
        }
    }

    public Pane getCenterPane() {
        return cent;
    }
    public Label getMoneyLabel() {
        return moneyLabel;
    }
    public void setMoneyLabel(String s) {
        moneyLabel.setText(s);
    }
}
