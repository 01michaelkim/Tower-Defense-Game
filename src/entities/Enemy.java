package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.geom.Point2D;

public abstract class Enemy extends Sprite {
    private Image image;
    private ImageView imageView;
    private Point2D pos;
    private int health;
    private int speed;
    private boolean isAlive;
    public Enemy (int healthMod, int speedMod) {
        super();
        health *= healthMod;
        speed *= speedMod;
        isAlive = true;
    }
    /**
    public void updatePos(double dx, double dy) {
        pos.setLocation(pos.getX() + dx , pos.getY() + dy);
    }
    public void setPos(double x, double y) {
        pos.setLocation(x, y);
    }
    public Point2D getPos() {
        return pos;
    }
     */
    public void takeDamage(int damage) {
        health -= damage;
    }
    public ImageView getImageView() {
        return imageView;
    }
}

