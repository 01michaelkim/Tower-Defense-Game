package controller;

import entities.*;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import model.GameModel;
import view.GameOverScreen;
import view.GameScreen;
import view.WinScreen;

import java.util.ArrayList;
import java.util.List;

public class GameScreenController extends ProgramScreenController {
    private ImageView startButton;
    private Image startButtonDefault = new Image("images//startButton1.png");
    private Image startButtonHovered = new Image("images//startButton2.png");

    private ImageView leftButton;
    private Image leftButtonDefault = new Image("images//larrow1.png");
    private Image leftButtonHovered = new Image("images//larrow2.png");

    private ImageView rightButton;
    private Image rightButtonDefault = new Image("images//rarrow1.png");
    private Image rightButtonHovered = new Image("images//rarrow2.png");

    private ImageView upgradeButton;
    private Image upgradeButtonDefault = new Image("images//upgradeButton1.png");
    private Image upgradeButtonHovered = new Image("images//upgradeButton2.png");

    private Image pointerImage = new Image("images//pointer-pixilart.png");

    private GraphicsContext g;

    private Label towerLabel;

    private BorderPane border;
    private int upgradePointer = 0;
    private Tower plant;
    private Tower notebook;
    private Tower fish;
    private Boss boss;
    private static ArrayList<Enemy> enemyList = new ArrayList<>();
    private boolean inGame = false;

    private static ArrayList<Tower> towers;

    public static ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public GameScreenController(GameScreen gameScreen) {
        this.setPlayer(gameScreen.getPlayer());

        this.startButton = gameScreen.getStartButton();
        this.leftButton = gameScreen.getLeftButton();
        this.rightButton = gameScreen.getRightButton();
        this.upgradeButton = gameScreen.getUpgradeButton();

        this.border = gameScreen.getBorder();
        this.g = gameScreen.getGraphicsContext();

        this.plant = gameScreen.getPlantTower();
        this.notebook = gameScreen.getNotebookTower();
        this.fish = gameScreen.getFishTower();

        this.towerLabel = gameScreen.getTowerLabel();
        towers = new ArrayList<>();
        this.currentStage = gameScreen.getStage();
        this.enemyList = gameScreen.getEnemyList();
        gameScreen.setTowerUpgraded(true);
        this.boss = new Boss(-100, 10);
    }

    public void resetGameParameters() {
        this.getPlayer().reset();
    }

    public void startButtonHandler() {
        this.startButton.setOnMouseEntered(e -> {
            this.startButton.setImage(this.startButtonHovered);
        });

        this.startButton.setOnMouseExited(e -> {
            this.startButton.setImage(this.startButtonDefault);
        });

        this.startButton.setOnMouseClicked(e -> {
            inGame = true;
            initCombat();
            startButton.setVisible(false);
        });
    }

    public int mod(int x, int m) {
        return (x % m + m) % m;
    }

    public void leftButtonHandler() {

        this.leftButton.setOnMouseEntered(e -> {
            this.leftButton.setImage(this.leftButtonHovered);
        });

        this.leftButton.setOnMouseExited(e -> {
            this.leftButton.setImage(this.leftButtonDefault);
        });

        this.leftButton.setOnMouseClicked(e -> {
            if (towers.size() != 0) {
                upgradePointer = mod(upgradePointer - 1, towers.size());
                towerLabel.setText(towers.get(upgradePointer).toString());
            } else {
                towerLabel.setText("Select Tower!");
            }
        });
        this.leftButton.setOnKeyReleased(e -> {
            if (towers.size() != 0) {
                upgradePointer = mod(upgradePointer - 1, towers.size());
                towerLabel.setText(towers.get(upgradePointer).toString());
            } else {
                towerLabel.setText("Select Tower!");
            }
        });
    }

    public void drawPointer(GraphicsContext g) {
        if (towers.size() != 0) {
            Point2D towerPos = towers.get(upgradePointer).getPos();
            g.drawImage(pointerImage, towerPos.getX(), towerPos.getY() - 25);
        }
    }

    public void rightButtonHandler() {
        this.rightButton.setOnMouseEntered(e -> {
            this.rightButton.setImage(this.rightButtonHovered);
        });

        this.rightButton.setOnMouseExited(e -> {
            this.rightButton.setImage(this.rightButtonDefault);
        });

        this.rightButton.setOnMouseClicked(e -> {
            if (towers.size() != 0) {
                upgradePointer = mod(upgradePointer + 1, towers.size());
                towerLabel.setText(towers.get(upgradePointer).toString());
            } else {
                towerLabel.setText("Select Tower!");
            }
        });
    }

    public void upgradeButtonHandler() {
        this.upgradeButton.setOnMouseEntered(e -> {
            this.upgradeButton.setImage(this.upgradeButtonHovered);
        });

        this.upgradeButton.setOnMouseExited(e -> {
            this.upgradeButton.setImage(this.upgradeButtonDefault);
        });

        this.upgradeButton.setOnMouseClicked(e -> {
            Tower currentTower = towers.get(upgradePointer);
            if (currentTower.getUpgradeCost() <= getPlayer().getMoney()) {
                if (!currentTower.upgraded()) {
                    currentTower.upgradeTower();
                    getPlayer().setMoney(getPlayer().getMoney() - currentTower.getUpgradeCost());
                }
            }
        });
    }

    public void initCombat() {
        if (GameModel.getDifficulty().equals("EASY")) {
            for (int i = 1; i <= 20; i++) {
                Overdude enemy = new Overdude(-i * 50, 20);
                enemyList.add(enemy);
            }
        } else if (GameModel.getDifficulty().equals("MEDIUM")) {
            for (int i = 1; i <= 20; i++) {
                Pizza enemy = new Pizza(-i * 50, 20);
                enemyList.add(enemy);
            }
        } else if (GameModel.getDifficulty().equals("HARD")) {
            for (int i = 1; i <= 20; i++) {
                Benzo enemy = new Benzo(-i * 50, 20);
                enemyList.add(enemy);
            }
        }
    }

    public void updateEnemies(GameScreen gameScreen) {
        if (!enemyList.isEmpty()) {
            List<Enemy> found = new ArrayList<>();
            for (Enemy enemy : enemyList) {
                enemy.checkPath();
                if (enemy.getPos().getX() == 400 && enemy.getPos().getY() == 380) {
                    found.add(enemy);
                    updateHealth(gameScreen, enemy);
                } else if (enemy.getHealth() < 0) {
                    found.add(enemy);
                    updateMoney(gameScreen, enemy);
                    GameModel.setNumdead(GameModel.getNumdead() + 1);
                }
                enemy.updatePos();
            }
            enemyList.removeAll(found);
        } else {
            if (inGame) {
                gameScreen.setBossTime(true);
                boss.checkPath();
                if (boss.getPos().getX() == 400 && boss.getPos().getY() == 370) {
                    updateHealth(gameScreen, boss);
                } else if (boss.getHealth() < 0) {
                    gameScreen.setBossTime(false);
                    inGame = false;
                    updateMoney(gameScreen, boss);
                    win();
                }
                boss.updatePos();
            }
        }
    }

    public void updateMoney(GameScreen gameScreen, Enemy enemy) {
        getPlayer().setMoney(getPlayer().getMoney() + enemy.getCash());
        gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
    }

    public void updateMoneyLabel(GameScreen gameScreen) {
        gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
    }

    public void updateHealth(GameScreen gameScreen, Enemy enemy) {
        getPlayer().setHealth(getPlayer().getHealth() - enemy.getDamage());
        gameScreen.setHealthLabel("Health: " + getPlayer().getHealth());
        if (getPlayer().getHealth() <= 0 && inGame) {
            inGame = false;
            setNextStage(new GameOverScreen());
            currentStage.close();
            currentStage = null;
            currentStage = nextStage;
            currentStage.show();
        }
    }

    public void drawEnemies(GraphicsContext g) {
        if (!enemyList.isEmpty()) {
            for (Enemy enemy: enemyList) {
                enemy.draw(g);
            }
        } else {
            if (inGame) {
                boss.draw(g);
            }
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
                    if (isImageEqual(db.getImage(), plant.getImageView().getImage())) {
                        towers.add(new Plant(event.getX() - 16, event.getY() - 16));
                        getPlayer().setMoney(getPlayer().getMoney() - plant.getPrice());
                        gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
                    } else if (isImageEqual(db.getImage(), notebook.getImageView().getImage())) {
                        towers.add(new Notebook(event.getX() - 16, event.getY() - 16));
                        getPlayer().setMoney(getPlayer().getMoney() - notebook.getPrice());
                        gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
                    } else if (isImageEqual(db.getImage(), fish.getImageView().getImage())) {
                        towers.add(new Fish(event.getX() - 16, event.getY() - 16));
                        getPlayer().setMoney(getPlayer().getMoney() - fish.getPrice());
                        gameScreen.setMoneyLabel("Money: " + getPlayer().getMoney());
                    }
                    success = true;
                }
                event.setDropCompleted(success);

                event.consume();
            }
        });
    }

    private boolean isImageEqual(Image firstImage, Image secondImage) {
        if (firstImage != null && secondImage == null) {
            return false;
        }
        if (firstImage == null) {
            return secondImage == null;
        }

        if (firstImage.getWidth() != secondImage.getWidth()) {
            return false;
        }
        if (firstImage.getHeight() != secondImage.getHeight()) {
            return false;
        }

        for (int x = 0; x < firstImage.getWidth(); x++) {
            for (int y = 0; y < firstImage.getHeight(); y++) {
                int firstArgb = firstImage.getPixelReader().getArgb(x, y);
                int secondArgb = secondImage.getPixelReader().getArgb(x, y);
                if (firstArgb != secondArgb) {
                    return false;
                }
            }
        }
        return true;
    }

    public void drawTowers(GraphicsContext g, GameScreen gameScreen) {
        if (towers == null) {
            throw new IllegalArgumentException("TowersList is null");
        }
        if (towers.size() != 0) {
            towerLabel.setText(towers.get(upgradePointer).toString());
        }
        for (Tower tower : towers) {
            tower.draw(g);
            if (!enemyList.isEmpty()) {
                for (Enemy enemy : enemyList) {
                    if (inRange(tower, enemy)) {
                        tower.drawLaser(g, tower, enemy);
                        tower.attack(enemy);
                    }

                }
            } else { //update drawTowers for Boss
                if (inGame) {
                    if (inRange(tower, boss)) {
                        tower.drawLaser(g, tower, boss);
                        tower.attack(boss);
                    }
                }
            }
        }
    }

    public void toggleTowers() {
        for (Tower tower : towers) {
            tower.toggle();
        }
    }

    public void toggleEnemies() {
        for (Enemy enemy: enemyList) {
            enemy.toggle();
        }
    }
    public void toggleBoss() {
        boss.toggle();
    }

    private boolean inRange(Tower tower, Enemy enemy) {
        return (Math.pow(tower.getPos().getX() - enemy.getPos().getX(), 2)
                + Math.pow(tower.getPos().getY() - enemy.getPos().getY(), 2))
                < Math.pow(tower.getRange(), 2);
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

    public void win() {
        if (getEnemyList().isEmpty()) {
            setNextStage(new WinScreen());
            currentStage.close();
            currentStage = null;
            currentStage = nextStage;
            currentStage.show();
        }
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }
}
