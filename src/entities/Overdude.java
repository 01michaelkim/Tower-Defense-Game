package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Overdude extends Enemy {
    private Image image;
    private ImageView imageView;
    private int cash = 50;
    private int damage = 50;
    private double transparency = 1.0;

    public Overdude(double x, double y) {
        super(3, 10, x, y);
        this.image = new Image("images/overdu-de.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
    }
    
    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public int getCash() {
        return cash;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public double getTransparency() {
        return transparency;
    }

    @Override
    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setGlobalAlpha(this.transparency);
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
        g.setGlobalAlpha(1);
    }
}
