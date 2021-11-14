package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.GameModel;

public class Fish extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    public int attack;
    public int range;
    public int fireRate; // per sec
    private String description;

    public Fish(double x, double y) {
        super(x, y);
        this.image = new Image("images/fish.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Fish Tower\n Cost: " + price;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
    }

    @Override
    public void attack(Enemy enemy, int frameCount) {

    }

    @Override
    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {
//        g.setFill(Color.GREEN);
//        g.setStroke(Color.GREEN);
//        g.setLineWidth(2);
//        g.beginPath();
//        g.moveTo(tower.getPos().getX(), tower.getPos().getY());
//        g.stroke();
//        g.lineTo(enemy.getPos().getX(), enemy.getPos().getY());
//        g.stroke();
    }

    @Override
    public String toString() {
        return "Fish Tower";
    }
}
