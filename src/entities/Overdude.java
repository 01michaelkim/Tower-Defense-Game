package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Overdude {
    private Image image;
    private ImageView imageView;
    public Overdude() {
        this.image = new Image("images/overdu-de.png");
        this.imageView = new ImageView(image);
    }
    public ImageView getImageView() {
        return imageView;
    }
}
