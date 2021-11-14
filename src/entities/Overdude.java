package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Overdude extends Enemy {
    private Image image;
    private ImageView imageView;
    public Overdude(double x, double y) {
        super(5,10, x, y);
        this.image = new Image("images/overdu-de.png");
        this.imageView = new ImageView(image);
    }
    
    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
    }
}
