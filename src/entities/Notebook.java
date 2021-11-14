package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public class Notebook extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;

    public Notebook(double x, double y) {
        super(x, y);
        this.image = new Image("images/notebook.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Notebook Tower\n Cost: " + price;
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

    @Override
    public String toString() {
        return "Notebook Tower";
    }
}
