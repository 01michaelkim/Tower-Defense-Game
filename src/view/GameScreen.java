package view;

//import entities.Tower1;
import entities.Fish;
import entities.Notebook;
import entities.Plant;
import entities.Tower;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import model.GameModel;


public class GameScreen {
    private int width;
    private int height;
    private int health = 100;
    private String characterName;
    private Label moneyLabel;
    private ToolBar towerMenu;
    private Tower plant;
    private Tower notebook;
    private Tower fish;
    private Pane cent;

    public GameScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Scene getScene() {
        // Set a border pane
        BorderPane border = new BorderPane();
        cent = new Pane();

        // Set the background image and add to the center of the border pane
        Image map = new Image("images//map.png");
        BackgroundImage backImage = new BackgroundImage(map,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backImage);
        cent.setBackground(background);
        border.setCenter(cent);

        // Set player stats and add to the bottom of the border pane
        VBox playerStats = new VBox();
        Label healthLabel = new Label("Health: " + health);
        moneyLabel = new Label("Money: " + GameModel.getMoney());
        characterName = ConfigurationScreen.getNamePrompt().getText();
        Label nameLabel = new Label(characterName);
        playerStats.getChildren().addAll(healthLabel, moneyLabel, nameLabel);
        playerStats.setAlignment(Pos.CENTER_LEFT);
        border.setBottom(playerStats);


        // Create Tower Menu
        VBox towerShop = new VBox();

        // Plant
        plant = new Plant();
        ImageView plantTower = plant.getImageView();

        // Notebook
        notebook = new Notebook();
        ImageView notebookTower = notebook.getImageView();

        // Fish
        fish = new Fish();
        ImageView fishTower = fish.getImageView();

        towerShop.getChildren().addAll(plantTower, notebookTower, fishTower);
        border.setRight(towerShop);

        setAction(plant, cent);
        setAction(notebook, cent);
        setAction(fish, cent);

        Tooltip plantToolTip = new Tooltip(plant.getDescription());
        Tooltip.install(plantTower, plantToolTip);

        Tooltip notebookToolTip = new Tooltip(notebook.getDescription());
        Tooltip.install(notebookTower, notebookToolTip);

        Tooltip fishToolTip = new Tooltip(fish.getDescription());
        Tooltip.install(fishTower, fishToolTip);

        Scene scene = new Scene(border, width, height);

        // Set the Style Sheet for the Scene
        scene.getStylesheets().add("resources/SceneStyle.css");
        return scene;
    }

    public void setAction(Tower image, Pane target) {
        ImageView source = image.getImageView();
        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (image.getPrice() <= GameModel.getMoney()) {
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
                    placed.setTranslateX(placed.getTranslateX() + event.getX() - 16);
                    placed.setTranslateY(placed.getTranslateY() + event.getY() - 16);

                    target.getChildren().add(placed);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                GameModel.setMoney(GameModel.getMoney() - image.getPrice());
                moneyLabel.setText("Money: " + GameModel.getMoney());
                event.setDropCompleted(success);

                event.consume();
            }
        });

//        source.setOnDragDone(new EventHandler<DragEvent>() {
//            public void handle(DragEvent event) {
//                /* the drag and drop gesture ended */
//                /* if the data was successfully moved, clear it */
//                if (event.getTransferMode() == TransferMode.MOVE) {
//                    source.setImage(new Image("images//plant.png"));
//                }
//                event.consume();
//            }
//        });
    }

    public void checkDifficulty(String s) {
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
    public Tower getTower(int i) {
        if (i == 1) {
            return plant;
        } else if (i == 2) {
            return notebook;
        } else if (i == 3) {
            return fish;
        } else {
            return null;
        }
    }
    public Pane getCenter() {
        return cent;
    }
}
