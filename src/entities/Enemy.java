package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import javafx.geometry.Point2D;

public abstract class Enemy {
    private Image image;
    private ImageView imageView;
    private Point2D pos;
    private int dx;
    private int dy;
    private int health = 1;
    private int speed = 1;
    private boolean isAlive;
    public Enemy (int healthMod, int speedMod, double x, double y) {
        pos = new Point2D(x, y);
        health *= healthMod;
        speed *= speedMod;
        dx = speed;
        dy = 0;
        isAlive = true;
    }
    public void updatePos() {
        pos = new Point2D(100, 100);

    }
    public void setPos(double x, double y) {
        pos = new Point2D(x, y);
    }
    public Point2D getPos() {
        return pos;
    }
    public void checkPath() {
        if (pos.getX() == 360 && pos.getY() == 40) {
            dx = 0;
            dy = speed;
        } else if (pos.getX() == 360 && pos.getY() == 240) {
            dx = -speed;
            dy = 0;
        } else if (pos.getX() == 40 && pos.getY() == 240) {
            dx = 0;
            dy = speed;
        } else if (pos.getX() == 40 && pos.getY() == 400) {
            dx = speed;
            dy = 0;
        }
    }
    public void draw(GraphicsContext g) {
        g.drawImage(image, pos.getX(), pos.getY());
    }
    public void takeDamage(int damage) {
        health -= damage;
    }
    public ImageView getImageView() {
        return imageView;
    }
}

