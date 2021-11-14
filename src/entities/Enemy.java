package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;

import javafx.geometry.Point2D;

public abstract class Enemy {
    private Point2D pos;
    private int dx;
    private int dy;
    private int health = 1;
    private int speed = 1;
    private boolean isAlive;

    public Enemy (int healthMod, int speedMod, double x, double y) {
        this.health *= healthMod;
        this.speed *= speedMod;
        this.pos = new Point2D(x, y);
        this.dx = speed;
        this.dy = 0;
        this.isAlive = true;
    }

    public abstract void draw(GraphicsContext g);

    public abstract ImageView getImageView();

    public void updatePos() {
        this.pos = new Point2D(this.pos.getX() + dx, this.pos.getY() + dy);

    }

    public void setPos(double x, double y) {
        this.pos = new Point2D(x, y);
    }

    public Point2D getPos() {
        return this.pos;
    }

    public void checkPath() {
        if (pos.getX() == 340 && pos.getY() == 20) {
            dx = 0;
            dy = speed;
        } else if (pos.getX() == 340 && pos.getY() == 220) {
            dx = -speed;
            dy = 0;
        } else if (pos.getX() == 20 && pos.getY() == 220) {
            dx = 0;
            dy = speed;
        } else if (pos.getX() == 20 && pos.getY() == 380) {
            dx = speed;
            dy = 0;
        }
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }
}

