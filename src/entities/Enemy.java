package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;

import javafx.geometry.Point2D;

public abstract class Enemy {
    private Point2D pos;
    private int dx;
    private int dy;
    private int startingHealth = 100;
    private int health = 100;
    private int speed = 1;
    private int cash = 50;
    private boolean isAlive;
    private final int imageSize = 50;
    private double transparency = 1.0;

    public Enemy(int healthMod, int speedMod, double x, double y) {
        this.health *= healthMod;
        this.startingHealth *= healthMod;
        this.speed *= speedMod;
        this.pos = new Point2D(x, y);
        this.dx = speed;
        this.dy = 0;
        this.isAlive = true;
    }

    public int getImageSize() {
        return this.imageSize;
    }

    public int getStartingHealth() {
        return startingHealth;
    }

    public abstract int getCash();
    public abstract int getDamage();

    public double getTransparency() {
        return transparency;
    }
    public void setTransparency(double transparency) {
        this.transparency = transparency;
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
    public boolean getisAlive() {
        return this.isAlive;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            isAlive = false;
        } else {
            this.transparency = (.3 + (double)this.health/(double)this.startingHealth);
        }
    }
    public abstract void toggle();
}

