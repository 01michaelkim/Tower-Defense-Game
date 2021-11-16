package view;


import controller.GameScreenController;
import entities.*;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class GameScreen extends ProgramScreen {
    private final int width = 550;
    private final int height = 600;
    private Label characterName;
    private Label healthLabel;
    private Label moneyLabel;
    private HashMap<String, Tower> towers;
    private static ImageView startButton;
    private BorderPane border;
    private GameScreenController controller;
    private Canvas canvas;
    private final int canvasWidth = 500;
    private final int canvasLength = 500;
    private GraphicsContext g;
    private boolean inGame = true;
    private double gameTick = 1e8;
    private static ArrayList<Enemy> enemyList = new ArrayList<>();

    public GameScreen(Player player) {
        this.setPlayer(player);
        this.initStage(this.width, this.height);
        this.initStageElements();
        this.initController();
        this.loopGame();
    }

    @Override
    public void initController() {
        this.controller = new GameScreenController(this);
        this.startController();
    }

    public void startController() {
        this.currentStage.show();
        this.controller.resetGameParameters();

        for (String key: towers.keySet()) {
            this.controller.dragDropHandler(this, towers.get(key));
        }

        this.controller.startButtonHandler();
    }

    public void initStageElements() {
        towers = new HashMap<>();
        border = new BorderPane();

        this.populateTowers();
        this.createBackground();
        this.createPlayerDataPane();
        this.createShopMenu();
        this.createStage();
    }

    public void populateTowers() {
        towers.put("Plant", new Plant(0, 0));
        towers.put("Notebook", new Notebook(0, 0));
        towers.put("Fish", new Fish(0, 0));
        System.out.println(towers.toString());
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
        this.canvas = new Canvas(canvasWidth, canvasLength);
        centerPane.getChildren().add(canvas);
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

    public void loopGame() {
        this.g = canvas.getGraphicsContext2D();
        AnimationTimer timer = new MyTimer();
        timer.start();
    }

    public void doGameCycle() {
        g.clearRect(0, 0, canvasWidth, canvasLength);
        controller.drawTowers(g, this);
        controller.updateEnemies(this);
        controller.drawEnemies(g);
    }

    public static ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public static ImageView getStartButton() {
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

    private class MyTimer extends AnimationTimer {
        private long prevTime = 0;

        @Override
        public void handle(long a) {
            long dt = a - prevTime;

            if (dt > gameTick) {
                prevTime = a;
                doGameCycle();
            }
        }
    }
}

