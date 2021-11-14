package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.GameModel;

public class Notebook extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;
    private Point2D pos;
    private int range = 200;
    private int attack = 10;

    public Notebook(double x, double y) {
        super();
        this.pos = new Point2D(x, y);
        this.image = new Image("images/notebook.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Notebook Tower\n Cost: " + price;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public Point2D getPos() {
        return this.pos;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(this.image, this.pos.getX(), this.pos.getY());
    }

    @Override
    public void attack(Enemy enemy) {
        enemy.setHealth(enemy.getHealth() - this.attack);
    }

    @Override
    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {
        g.setFill(Color.GREEN);
        g.setStroke(Color.GREEN);
        g.setLineWidth(2);
        g.beginPath();
        g.moveTo(tower.getPos().getX() + 16, tower.getPos().getY() + 16);
        g.stroke();
        g.lineTo(enemy.getPos().getX() + 16, enemy.getPos().getY() + 16);
        g.stroke();
    }

    @Override
    public String toString() {
        return "Notebook Tower";
    }
}
