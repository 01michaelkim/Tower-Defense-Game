package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public abstract class Tower {
    private final int imageSize = 50;
    public int price;
    private Point2D pos;

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

    public Point2D getPos() {
        return this.pos;
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public abstract int getPrice();

    public abstract ImageView getImageView();
    public abstract String getDescription();
    public abstract void draw(GraphicsContext g);
    public abstract void attack(Enemy enemy, int frameCount);
    public abstract void drawLaser(GraphicsContext g, Tower tower, Enemy enemy);
}