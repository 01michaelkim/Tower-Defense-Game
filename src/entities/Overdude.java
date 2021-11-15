package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Overdude extends Enemy {
    private Image image;
    private ImageView imageView;
    public Overdude(double x, double y) {
        super(10,10, x, y);
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
    public void draw(GraphicsContext g) {
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
    }
}
