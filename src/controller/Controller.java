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
    private Stage mainWindow;
    private GameModel gameModel;
    private final int width = 500;
    private final int height = 500;
    private ImageView startButton;
    private ImageView restartButton;
    private ImageView exitButton;
    private Button playButton;
    private TextField nameLabel;
    private ComboBox dropdown;

    private ImageView placed;
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
        startButton = screen.getStartButton();
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
        playButton = screen.getPlayButton();
        dropdown = screen.getDropdown();
        nameLabel = screen.getNameLabel();
        playButton.setOnAction(e -> {
            if (screen.checkName(screen.getNameLabel().getText()) && screen.checkDrop()) {
                GameModel.setDifficulty(dropdown.getValue().toString());
                GameModel.setCharacterName(nameLabel.getText());
                initGameScreen();
            }
        });

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void initGameScreen() {
        GameScreen screen = new GameScreen(width + 50, height + 100);
        GameModel.reset();
        Tower plantTower = screen.getTower(1);
        Tower notebookTower = screen.getTower(2);
        Tower fishTower = screen.getTower(3);

        Label moneyLabel = screen.getMoneyLabel();

        Path path = screen.createPath();
        startButton = screen.getStartButton();
        startButton.setOnMouseClicked(e -> {
            ArrayList<ImageView> enemyList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                Overdude enemy = new Overdude();
                enemyList.add(enemy.getImageView());
            }

            Rectangle brain = new Rectangle(50, 50);
            brain.setX(450);
            brain.setY(400);

            for (ImageView element: enemyList) {
                screen.getBorder().getChildren().add(element);
                PathTransition transition = new PathTransition();
                transition.setDuration(Duration.seconds(25));
                transition.setPath(path);
                transition.setNode(element);
                transition.play();
                AnimationTimer collisionTimer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        if (element.getBoundsInParent().intersects(brain.getBoundsInParent())) {
                            if (element.isVisible()) {
                                element.setVisible(false);
                                GameModel.setHealth(GameModel.getHealth() - 50);
                                screen.setHealthLabel("Health: " + GameModel.getHealth());
                                if (GameModel.getHealth() == 0) {
                                    initGameOverScreen();
                                }
                            }
                        }
                    }
                };

                collisionTimer.start();
                screen.delaySpawn(500);
            }
            startButton.setVisible(false);
        });
        startButton.setOnMouseEntered(e -> screen.toggleStartButton());
        startButton.setOnMouseExited(e -> screen.toggleStartButton());

        dragDrop(plantTower, screen);
        dragDrop(notebookTower, screen);
        dragDrop(fishTower, screen);

        screen.checkDifficulty(GameModel.getDifficulty());
        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();


    }
    private void initGameOverScreen() {
        GameOverScreen screen = new GameOverScreen(width, height);
        // Create the restart button for the Game Over Screen
        restartButton = screen.getRestartButton();
        restartButton.setOnMouseClicked(e -> initWelcomeScreen());
        restartButton.setOnMouseEntered(e -> screen.toggleRestartButton());
        restartButton.setOnMouseExited(e -> screen.toggleRestartButton());

        //Create the exit button for the GameOver Screen
        exitButton = screen.getExitButton();
        exitButton.setOnMouseClicked(e -> {
            GameModel.setGameClosed();
            mainWindow.close(); });
        exitButton.setOnMouseEntered(e -> screen.toggleExitButton());
        exitButton.setOnMouseExited(e -> screen.toggleExitButton());

        Scene scene = screen.getScene();
        mainWindow.setScene(scene);
        mainWindow.show();
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

    private void dragDrop(Tower tower, GameScreen screen) {
        ImageView source = tower.getImageView();
        Pane target = screen.getCenterPane();
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (tower.getPrice() <= GameModel.getMoney()) {
                    /* drag was detected, start a drag-and-drop gesture*/
                    /* allow any transfer mode */
                    Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                    /* Put a image on a dragboard */
                    ClipboardContent content = new ClipboardContent();
                    content.putImage(source.getImage());
                    db.setContent(content);
                }
                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target && event.getDragboard().hasImage()
                        && !screen.isPath(event.getSceneX(), event.getSceneY())) {
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {
                    placed = new ImageView(db.getImage());
                    //Translate translate = new Translate();
                    //placed.relocate(event.getSceneX(), event.getSceneY());
                    placed.setTranslateX(placed.getTranslateX() + event.getX() - 16);
                    placed.setTranslateY(placed.getTranslateY() + event.getY() - 16);

                    target.getChildren().add(placed);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */

                GameModel.setMoney(GameModel.getMoney() - tower.getPrice());
                screen.setMoneyLabel("Money: " + GameModel.getMoney());
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }
}
