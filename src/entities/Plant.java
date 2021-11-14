package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.GameModel;

public class Plant extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;

    public Plant(double x, double y) {
        super(x, y);
        this.image = new Image("images/plant.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Plant Tower\n Cost: " + price;
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
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
    }

    @Override
    public void attack(Enemy enemy, int frameCount) {

    }

    @Override
    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {

    }

//    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {
//        g.setFill(Color.GREEN);
//        g.setStroke(Color.GREEN);
//        g.setLineWidth(2);
//        g.beginPath();
//        g.moveTo(tower.pos.getX(), tower.pos.getY());
//        g.stroke();
//        //g.lineTo(enemy.getX(), enemy.getY());
//        g.stroke();
//    }

    @Override
    public String toString() {
        return "Plant Tower";
    }
}
