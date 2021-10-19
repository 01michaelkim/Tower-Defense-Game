package controller;

import entities.Tower;
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
    private ImageView startButton;
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
        Tower plantTower = screen.getTower(1);
        Tower notebookTower = screen.getTower(2);
        Tower fishTower = screen.getTower(3);

        Label moneyLabel = screen.getMoneyLabel();

        dragDrop(plantTower, screen);
        dragDrop(notebookTower, screen);
        dragDrop(fishTower, screen);

        screen.checkDifficulty(GameModel.getDifficulty());
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
