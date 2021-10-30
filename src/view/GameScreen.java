package view;


import entities.Fish;
import entities.Notebook;
import entities.Plant;
import entities.Tower;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import model.GameModel;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;


public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private String characterName;
    private Label healthLabel;
    private Label moneyLabel;
    private ToolBar towerMenu;
    private Tower plant;
    private Tower notebook;
    private Tower fish;
    private Pane cent;
    private Image startButtonDefault = new Image("images//startButton1.png");
    private Image startButtonHovered = new Image("images//startButton2.png");
    private ImageView startButton;
    private BorderPane border;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
        plant = new Plant();
        notebook = new Notebook();
        fish = new Fish();
        cent = new Pane();
        this.startButton = new ImageView(startButtonDefault);
    }

    public Scene getScene() {
        // Set a border pane
        border = new BorderPane();

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
        //combat button
        HBox button = new HBox(startButton);

        VBox playerStats = new VBox();
        healthLabel = new Label("Health: " + GameModel.getHealth());
        moneyLabel = new Label("Money: " + GameModel.getMoney());
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);
        playerStats.getChildren().addAll(healthLabel, moneyLabel, nameLabel);
        BorderPane bringTogether = new BorderPane();
        bringTogether.setLeft(playerStats);
        bringTogether.setRight(button);
        bringTogether.setPadding(new Insets(0, 25, 25, 25));
        border.setBottom(bringTogether);


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
    //code reuse from welcome screen (for combat button)
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
    public void checkDifficulty(String s) {
        if (s.equals("EASY")) {
            GameModel.setHealth(300);
        } else if (s.equals("MEDIUM")) {
            GameModel.setHealth(200);
        } else if (s.equals("HARD")) {
            GameModel.setHealth(100);
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


    public Label getHealthLabel() {
        return healthLabel;
    }

    public void setHealthLabel(String s) {
        healthLabel.setText(s);
    }

    public Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-800, 40.0);
        LineTo line1 = new LineTo(360.0, 40.0);
        LineTo line2 = new LineTo(360.0, 240.0);
        LineTo line3 = new LineTo(40.0, 240.0);
        LineTo line4 = new LineTo(40.0, 400.0);
        LineTo line5 = new LineTo(450.0, 400.0);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5);
        return path;
    }

    // Delay the spawning of
    public void delaySpawn(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BorderPane getBorder() {
        return border;
    }
}

