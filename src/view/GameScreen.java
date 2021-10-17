package view;

//import entities.Tower1;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;


public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private int money = 500;
    private String characterName;
    private ToolBar towerMenu;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        // Set a border pane
        BorderPane border = new BorderPane();
        FlowPane cent = new FlowPane();

        // Create the Title of the Game Screen and add to the top of border pane
        // MIGHT REMOVE???
        HBox title = new HBox();
        Label label = new Label("College Student Tower Defense");
        title.getChildren().add(label);
        title.setAlignment(Pos.CENTER);
        border.setTop(title);

        // Set the background image and add to the center of the border pane

        Image map = new Image("images//map.png");
        BackgroundImage backImage = new BackgroundImage(map, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backImage);
        cent.setBackground(background);
        border.setCenter(cent);

        // Set player stats and add to the bottom of the border pane
        VBox playerStats = new VBox();
        Label healthLabel = new Label("Health: " + health);
        Label moneyLabel = new Label("Money: " + money);
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);
        playerStats.getChildren().addAll(healthLabel, moneyLabel, nameLabel);
        playerStats.setAlignment(Pos.CENTER_LEFT);
        border.setBottom(playerStats);


        // Create Tower Menu
        VBox towerShop = new VBox();
        //Tower1 firstTower = new Tower1();
        //ImageView firstTowerImage = firstTower.getImageView();
        //For now just using Image as placeholder for actual tower class
        Image tower1 = new Image("images//plant.png");
        ImageView firstTowerImage = new ImageView(tower1);
        firstTowerImage.setFitHeight(50);
        firstTowerImage.setFitWidth(50);
        towerShop.getChildren().add(firstTowerImage);
        border.setRight(towerShop);

        //Drag and Drop stuff
        ImageView source = firstTowerImage;
        FlowPane target = cent;
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putImage(source.getImage());
                db.setContent(content);

                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != target &&
                        event.getDragboard().hasImage() && !isPath(event.getSceneX(), event.getSceneY())) {
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
                    ImageView placed = new ImageView(db.getImage());
                    //Translate translate = new Translate();
                    //placed.relocate(event.getSceneX(), event.getSceneY());
                    placed.setTranslateX(placed.getTranslateX() + event.getX());
                    placed.setTranslateY(placed.getTranslateY() + event.getY());
                    target.getChildren().add(placed);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });
        source.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag and drop gesture ended */
                /* if the data was successfully moved, clear it */
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setImage(new Image("images//plant.png"));
                }
                event.consume();
            }
        });
        Scene scene = new Scene(border, width, height);

        // Set the Style Sheet for the Scene
        scene.getStylesheets().add("resources/SceneStyle.css");
        return scene;
    }
    public void checkMoney(String s) {
        if (s.equals("EASY")) {
            health = 300;
        } else if (s.equals("MEDIUM")) {
            health = 200;
        } else if (s.equals("HARD")) {
            health = 100;
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

}
