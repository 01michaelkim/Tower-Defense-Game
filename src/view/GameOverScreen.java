package view;

import controller.GameOverScreenController;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class GameOverScreen extends ProgramScreen {
    private final int width = 500;
    private final int height = 500;
    private ImageView restartButton;
    private ImageView exitButton;
    private VBox screenPane;
    private GameOverScreenController controller;


    public GameOverScreen() {
        this.InitStage(this.width, this.height);
        this.InitStageElements();
        this.InitController();
    }

    @Override
    public void InitController() {
        this.controller = new GameOverScreenController(this);
        this.StartController();
    }

    public void StartController() {
        this.currentStage.show();
        this.controller.startButtonHandlers();
    }

    public void InitStageElements() {
        this.screenPane = new VBox();

        this.createGameOverImage();
        this.createButtons();
        this.createStage();
    }

    public void createGameOverImage() {
        ImageView gameOverImage = new ImageView(new Image("images//gameOver.png"));
        this.screenPane.getChildren().add(gameOverImage);
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
        this.screenPane.setSpacing(200);
        this.pane.setCenter(this.screenPane);
    }

    public ImageView getRestartButton() {
        return restartButton;
    }
    public ImageView getExitButton() {
        return exitButton;
    }
}
