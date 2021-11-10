package controller;

import entities.Overdude;
import entities.Tower;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.GameModel;
import view.ConfigurationScreen;
import view.GameScreen;
import view.WelcomeScreen;
import view.GameOverScreen;

import java.util.ArrayList;

public class Controller extends Application {
    private ImageView startButton;
    private ImageView restartButton;
    private ImageView exitButton;
    private Button playButton;
    private TextField nameLabel;
    private ComboBox dropdown;
    private ImageView placed;

    @Override
    public void start(Stage primaryStage) throws Exception {
        WelcomeScreen screen = new WelcomeScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ImageView getStart() {
        return startButton;
    }
    public ImageView getRestart() {
        return restartButton;
    }
    public ImageView getExit() {
        return exitButton;
    }
    public Button getPlayButton() {
        return playButton;
    }
    public TextField getNameLabel() {
        return nameLabel;
    }
    public ComboBox getDropdown() {
        return dropdown;
    }
    public ImageView getPlaced() {
        return placed;
    }

}
