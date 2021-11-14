package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

import java.awt.geom.Point2D;

public abstract class Tower {
    //private Image image;
    private ImageView imageView;
    public Point2D pos;
    public int damValue;
    public int price;

    /**
     * Which things go in child/parent constructors can definitely be modified here but careful with errors.
     */

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
    public abstract int getPrice();

    public abstract ImageView getImageView();
    public abstract String getDescription();
    public abstract void attack();

    public abstract void draw(GraphicsContext g);
}