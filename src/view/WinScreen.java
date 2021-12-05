package view;

import controller.WinScreenController;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import model.GameModel;
import model.Player;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.*;


import entities.Enemy;
import javafx.scene.Group;


public class WinScreen extends ProgramScreen {
    private final int width = 500;
    private final int height = 500;
    private static ImageView restartButton;
    private static ImageView exitButton;
    private VBox screenPane;
    private WinScreenController controller;


    public WinScreen() {
        this.initStage(this.width, this.height);
        this.initStageElements();
        this.initController();
    }

    @Override
    public void initController() {
        this.controller = new WinScreenController(this);
        this.startController();
    }

    public void startController() {
        this.currentStage.show();
        this.controller.startButtonHandlers();
    }

    public void initStageElements() {
        this.screenPane = new VBox();
        this.createWinImage();
        this.createStats();
        this.createButtons();
        this.createStage();
    }

    public void createWinImage() {
        ImageView winImage = new ImageView(new Image("images//you-won.png"));
        this.screenPane.getChildren().add(winImage);
    }

    public void createStats() {
        Label text1 = new Label();
        Label text2 = new Label();
        Label text3 = new Label();
        int health = Player.getHealth();
        int money = Player.getMoney();
        String h = Integer.toString(health);
        String m = Integer.toString(money);
        text1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        text1.setText("Player Health: " + h);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        text2.setText("Player Money: " + m);
//        text2.setX(100);
//        text2.setY(145);
        int dead = GameModel.getNumdead();
        String d = Integer.toString(dead);
        text3.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        text3.setText("Kills: " + d);
//        text3.setX(100);
//        text3.setY(160);
        VBox vbox = new VBox(text1, text2, text3);
        vbox.setAlignment(Pos.CENTER);
        this.screenPane.getChildren().add(vbox);
    }

    public void createButtons() {
        HBox restartButtonPane = this.createRestartButton();
        HBox exitButtonPane = this.createExitButton();
        HBox buttons = new HBox(restartButtonPane, exitButtonPane);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(50);
        this.screenPane.getChildren().add(buttons);
    }

    public HBox createRestartButton() {
        this.restartButton = new ImageView(new Image("images//restartButton1.png"));
        HBox buttonPane = new HBox(this.restartButton);
        buttonPane.setAlignment(Pos.CENTER_LEFT);
        return buttonPane;
    }

    public HBox createExitButton() {
        this.exitButton = new ImageView(new Image("images//exitButton1.png"));
        HBox buttonPane = new HBox(this.exitButton);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        return buttonPane;
    }

    public void createStage() {
        this.screenPane.setAlignment(Pos.TOP_CENTER);
        this.screenPane.setSpacing(50);
        this.pane.setCenter(this.screenPane);
    }

    public static ImageView getRestartButton() {
        return restartButton;
    }
    public static ImageView getExitButton() {
        return exitButton;
    }
}