package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import model.GameModel;

public abstract class Tower {
    private final int imageSize = 50;
    private int price;
    private Point2D pos;
    private int range;
    private int attack;

    public Tower() {
        if (GameModel.getDifficulty() == "EASY") {
            price = 50;
        }
        if (GameModel.getDifficulty() == "MEDIUM") {
            price = 100;
        }
        if (GameModel.getDifficulty() == "HARD") {
            price = 150;
        }
    }

    public Tower(double x, double y) {
        this.pos = new Point2D(x, y);
        if (GameModel.getDifficulty() == "EASY") {
            price = 50;
        }
        if (GameModel.getDifficulty() == "MEDIUM") {
            price = 100;
        }
        if (GameModel.getDifficulty() == "HARD") {
            price = 150;
        }
    }

    public abstract Point2D getPos();

    public int getImageSize() {
        return this.imageSize;
    }

    public abstract int getAttack();

    public abstract int getPrice();
    public abstract int getRange();
    public abstract ImageView getImageView();
    public abstract String getDescription();
    public abstract void draw(GraphicsContext g);
    public abstract void attack(Enemy enemy);
    public abstract void drawLaser(GraphicsContext g, Tower tower, Enemy enemy);
}