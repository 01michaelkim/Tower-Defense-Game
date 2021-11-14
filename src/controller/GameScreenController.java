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
import java.util.Iterator;
import java.util.List;

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
    private ArrayList<Enemy> enemyList = new ArrayList<>();

    private ArrayList<Tower> towers = new ArrayList<>();

    public GameScreenController(GameScreen gameScreen) {
        this.setPlayer(gameScreen.getPlayer());

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
            initCombat();
        });
    }

    public void initCombat() {
        for (int i = 0; i < 1; i++) {
            Overdude enemy = new Overdude(0, 20);
            enemyList.add(enemy);
        }
    }

    public void updateEnemies(GameScreen gameScreen) {
        if (!enemyList.isEmpty()) {
            List<Enemy> found = new ArrayList<>();
            for (Enemy enemy : enemyList) {
                enemy.checkPath();
                System.out.println("Enemy Position: " + enemy.getPos().getX() + ", " + enemy.getPos().getY());
                if (enemy.getPos().getX() == 400 && enemy.getPos().getY() == 380) {
                    found.add(enemy);
                    updateHealth(gameScreen);
                }
                enemy.updatePos();
            }
            enemyList.removeAll(found);
        }
    }

    public void updateHealth(GameScreen gameScreen) {
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

    public void drawEnemies(GraphicsContext g) {
        for (Enemy enemy: enemyList) {
            enemy.draw(g);
        }
    }

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
                    System.out.println("GAY");
                    if (tower instanceof Plant) {
                        towers.add(new Plant(event.getX() - 16,event.getY() - 16));
                    } else if (tower instanceof Notebook) {
                        towers.add(new Notebook(event.getX() - 16,event.getY() - 16));
                    } else if (tower instanceof Fish) {
                        towers.add(new Fish(event.getX() - 16,event.getY() - 16));
                    }
                    success = true;
                }
                getPlayer().setMoney(getPlayer().getMoney() - tower.getPrice());
                gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    public void drawTowers(GraphicsContext g) {
        if (towers == null) {
            throw new IllegalArgumentException("TowersList is null");
        }
        for (Tower tower : towers) {
            tower.draw(g);
        }
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
}
