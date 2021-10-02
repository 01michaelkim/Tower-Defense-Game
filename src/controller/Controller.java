package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameModel;
import view.ConfigurationScreen;
import view.GameScreen;
import view.WelcomeScreen;

public class Controller extends Application {
    private Stage mainWindow;
    private GameModel gameModel;
    private final int width = 500;
    private final int height = 500;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Welcome to Tower Defense");
        gameModel = new GameModel();
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen screen = new WelcomeScreen(width, height);
        Button startButton = screen.getStartButton();
        startButton.setOnAction(e -> initConfigurationScreen());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initConfigurationScreen() {
        ConfigurationScreen screen = new ConfigurationScreen(width, height);
        Button playButton = screen.getStartButton();
        playButton.setOnAction(e -> initGameScreen());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initGameScreen() {
        GameScreen screen = new GameScreen(width, height);

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
