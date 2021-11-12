package entities;

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


}