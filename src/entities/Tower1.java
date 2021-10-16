package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tower1 {
    private Image image;
    private ImageView imageView;

    public Tower1() {
        this.image = new Image("images/tower1.png");
        this.imageView = new ImageView(image);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
