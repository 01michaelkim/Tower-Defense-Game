package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.GameModel;
import view.ConfigurationScreen;
import view.GameScreen;
import view.WelcomeScreen;

public class Controller extends Application {
    private Stage mainWindow;
    private GameModel gameModel;
    private final int width = 500;
    private final int height = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainWindow = primaryStage;
        mainWindow.setTitle("Welcome to Tower Defense");
        gameModel = new GameModel();
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen screen = new WelcomeScreen(width, height);

        // Create the start button for the welcome screen
        ImageView startButton = screen.getStartButton();
        startButton.setOnMouseClicked(e -> initConfigurationScreen());
        startButton.setOnMouseEntered(e -> screen.toggleStartButton());
        startButton.setOnMouseExited(e -> screen.toggleStartButton());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initConfigurationScreen() {
        ConfigurationScreen screen = new ConfigurationScreen(width, height / 2);

        // Create the play button screen which sets the difficulty, character name,
        // and moves to game screen when pressed
        Button playButton = screen.getPlayButton();
        ComboBox dropdown = screen.getDropdown();
        TextField nameLabel = screen.getNameLabel();
        playButton.setOnAction(e -> {
            gameModel.setDifficulty(dropdown.getValue().toString());
            gameModel.setCharacterName(nameLabel.getText());
            initGameScreen();
        });

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initGameScreen() {
        GameScreen screen = new GameScreen(width, height + 50);
        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
