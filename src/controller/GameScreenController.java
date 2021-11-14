package controller;

import entities.*;
import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.GameOverScreen;
import view.GameScreen;

import java.util.ArrayList;

public class GameScreenController extends ProgramScreenController {
    private ImageView startButton;
    private Image startButtonDefault = new Image("images//startButton1.png");
    private Image startButtonHovered = new Image("images//startButton2.png");
    private BorderPane border;
    private Tower plant;
    private Tower notebook;
    private Tower fish;
    private Path path;
    private GameScreen gameScreen;

    private ArrayList<Tower> towers = new ArrayList<>();

    public GameScreenController(GameScreen gameScreen) {
        this.setPlayer(gameScreen.getPlayer());

        this.path = createPath();

        this.startButton = gameScreen.getStartButton();
        this.border = gameScreen.getBorder();

        this.plant = gameScreen.getPlantTower();
        this.notebook = gameScreen.getNotebookTower();
        this.fish = gameScreen.getFishTower();

        this.currentStage = gameScreen.getStage();
    }

    public void resetGameParameters() {
        this.getPlayer().reset();
    }

    public void startButtonHandler(GameScreen gameScreen) {
        this.startButton.setOnMouseEntered(e -> {
            this.startButton.setImage(this.startButtonHovered);
        });

        this.startButton.setOnMouseExited(e -> {
            this.startButton.setImage(this.startButtonDefault);
        });

        this.startButton.setOnMouseClicked(e -> {
            initCombat(gameScreen);
        });
    }
    //need to modify this so that we are moving around and tracking enemy objects
    //not just imageView nodes
    public void initCombat(GameScreen gameScreen) {
        ArrayList<Enemy> enemyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Overdude enemy = new Overdude();
            enemyList.add(enemy);
        }

        Rectangle brain = new Rectangle(50, 50);
        brain.setX(450);
        brain.setY(400);


        for (Enemy element: enemyList) {
            this.border.getChildren().add(element.getImageView());
            PathTransition transition = new PathTransition();
            transition.setDuration(Duration.seconds(25));
            transition.setPath(this.path);
            transition.setNode(element.getImageView());
            transition.play();
            AnimationTimer collisionTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (element.getImageView().getBoundsInParent().intersects(brain.getBoundsInParent())) {
                        if (element.isVisible()) {
                            element.setVisible(false);

                            getPlayer().setHealth(getPlayer().getHealth() - 50);
                            gameScreen.setHealthLabel("Health: " + getPlayer().getHealth());

                            if (getPlayer().getHealth() == 0) {
                                setNextStage(new GameOverScreen());
                                currentStage.close();
                                currentStage = null;
                                currentStage = nextStage;
                                currentStage.show();
                            }
                        }
                    }
                }
            };

            collisionTimer.start();
            delaySpawn(500);
        }
        startButton.setVisible(false);
    }
    //Everything is being recognized and drawn as Fish Towers, I suspect the problem is somewhere in here, not sure
    public void dragDropHandler(GameScreen gameScreen, Tower tower) {
        ImageView source = tower.getImageView();
        Node target = gameScreen.getBorder().getCenter();

        source.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (tower.getPrice() <= getPlayer().getMoney()) {
                    Dragboard db = source.startDragAndDrop(TransferMode.ANY);

                    ClipboardContent content = new ClipboardContent();
                    content.putImage(source.getImage());
                    db.setContent(content);
                }
                event.consume();
            }
        });

        target.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                if (event.getGestureSource() != target && event.getDragboard().hasImage()
                        && !isPath(event.getSceneX(), event.getSceneY())) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        target.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasImage()) {

                    if (tower instanceof Plant) {
                        towers.add(new Plant(event.getX() - 16,event.getY() - 16));
                    } else if (tower instanceof Notebook) {
                        towers.add(new Notebook(event.getX() - 16,event.getY() - 16));
                    } else if (tower instanceof Fish) {
                        towers.add(new Fish(event.getX() - 16,event.getY() - 16));
                    }
                    /**
                    ImageView placed = new ImageView(db.getImage());

                    placed.setTranslateX(placed.getTranslateX() + event.getX() - 16);
                    placed.setTranslateY(placed.getTranslateY() + event.getY() - 16);
                    ((Pane) target).getChildren().add(placed);
                    */
                    success = true;
                }
                getPlayer().setMoney(getPlayer().getMoney() - tower.getPrice());
                gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    public void drawTowers(GraphicsContext g, ArrayList<Tower> towers) {
        if (towers == null) {
            throw new IllegalArgumentException("towerList is null");
        }
        System.out.println(towers.size());
        for (Tower tower : towers) {
            tower.draw(g);
            System.out.println(tower.toString());
        }
    }

    public void delaySpawn(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Path createPath() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(-800, 40.0);
        LineTo line1 = new LineTo(360.0, 40.0);
        LineTo line2 = new LineTo(360.0, 240.0);
        LineTo line3 = new LineTo(40.0, 240.0);
        LineTo line4 = new LineTo(40.0, 400.0);
        LineTo line5 = new LineTo(450.0, 400.0);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5);
        return path;
    }

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

    public ArrayList<Tower> getTowers() {
        return towers;
    }

}
