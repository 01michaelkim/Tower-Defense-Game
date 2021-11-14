package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Benzo extends Enemy {
    private Image image;
    private ImageView imageView;
    public Benzo(double x, double y) {
        super(5,10, x, y);
        this.image = new Image("images/benzo.png");
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
