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
    private Point2D pos;

    public Plant(double x, double y) {
        super();
        this.image = new Image("images/plant.png");
        this.imageView = new ImageView(image);
        this.pos = new Point2D(x,y);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
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
    public void attack() {

    }
    //draw Plant Tower
    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(image, pos.getX(), pos.getY());
    }
    //draw Laser Method?
    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {
        g.setFill(Color.GREEN);
        g.setStroke(Color.GREEN);
        g.setLineWidth(2);
        g.beginPath();
        g.moveTo(tower.pos.getX(), tower.pos.getY());
        g.stroke();
        g.lineTo(enemy.getX(), enemy.getY());
        g.stroke();
    }

    @Override
    public String toString() {
        return "Plant Tower";
    }
}
