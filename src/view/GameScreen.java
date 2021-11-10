package view;


import controller.GameScreenController;
import entities.Fish;
import entities.Notebook;
import entities.Plant;
import entities.Tower;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.GameModel;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import model.Player;

import java.util.HashMap;


public class GameScreen extends ProgramScreen {
    private final int width = 550;
    private final int height = 600;
    private Label characterName;
    private Label healthLabel;
    private Label moneyLabel;
    private HashMap<String, Tower> towers;
    private ImageView startButton;
    private BorderPane border;
    private GameScreenController controller;

    public GameScreen(Player player) {
        this.setPlayer(player);
        this.InitStage(this.width, this.height);
        this.InitStageElements();
        this.InitController();
    }

    @Override
    public void InitController() {
        this.controller = new GameScreenController(this);
        this.StartController();
    }

    public void StartController() {
        this.currentStage.show();
        this.controller.resetGameParameters();
        this.controller.dragDropHandler(this, towers.get("Plant"));
        this.controller.dragDropHandler(this, towers.get("Notebook"));
        this.controller.dragDropHandler(this, towers.get("Fish"));
        this.controller.startButtonHandler(this);
    }

    public void InitStageElements() {
        towers = new HashMap<>();
        border = new BorderPane();

        this.populateTowers();
        this.createBackground();
        this.createPlayerDataPane();
        this.createShopMenu();
        this.createStage();
    }

    public void populateTowers() {
        towers.put("Plant", new Plant());
        towers.put("Notebook", new Notebook());
        towers.put("Fish", new Fish());
    }

    public void createBackground() {
        Image map = new Image("images//map2.png");
        BackgroundImage backImage = new BackgroundImage(map,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backImage);
        Pane centerPane = new Pane();
        centerPane.setBackground(background);
        this.border.setCenter(centerPane);
    }

    public void createPlayerDataPane() {
        startButton = new ImageView(new Image("images//startButton1.png"));
        HBox button = new HBox(startButton);

        VBox playerStats = new VBox();
        healthLabel = new Label("Health: " + getPlayer().getHealth());
        moneyLabel = new Label("Money: " + getPlayer().getMoney());
        characterName = new Label("Name: " + getPlayer().getCharacterName());
        playerStats.getChildren().addAll(healthLabel, moneyLabel, characterName);

        BorderPane bringTogether = new BorderPane();
        bringTogether.setLeft(playerStats);
        bringTogether.setRight(button);
        bringTogether.setPadding(new Insets(0, 25, 25, 25));
        this.border.setBottom(bringTogether);
    }

    public void createShopMenu() {
        VBox towerShop = new VBox();
        towerShop.getChildren().addAll(
                createShopTower(towers.get("Plant")),
                createShopTower(towers.get("Notebook")),
                createShopTower(towers.get("Fish"))
        );
        this.border.setRight(towerShop);
    }

    public ImageView createShopTower(Tower tower) {
        ImageView towerImageView = tower.getImageView();
        Tooltip towerToolTip = new Tooltip(tower.getDescription());
        Tooltip.install(towerImageView, towerToolTip);
        return towerImageView;
    }

    public void createStage() {
        this.pane.setCenter(border);
    }

    public ImageView getStartButton() {
        return startButton;
    }

    public HashMap<String, Tower> getTowers() {
        return towers;
    }

    public Tower getPlantTower() {
        return towers.get("Plant");
    }

    public Tower getNotebookTower() {
        return towers.get("Notebook");
    }

    public Tower getFishTower() {
        return towers.get("Fish");
    }

    public Label getMoneyLabel() {
        return moneyLabel;
    }

    public void setMoneyLabel(String newMoney) {
        moneyLabel.setText(newMoney);
    }

    public Label getHealthLabel() {
        return healthLabel;
    }

    public void setHealthLabel(String newHealth) {
        healthLabel.setText(newHealth);
    }

    public BorderPane getBorder() {
        return border;
    }
}

