package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BrainMonument {
    private Image image;
    private ImageView imageView;
    private int price;

    public BrainMonument() {
        this.image = new Image("images/brain.png");
        this.imageView = new ImageView(image);
    }
}
