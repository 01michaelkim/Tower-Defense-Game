package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Overdude extends Enemy{
    private Image image;
    private ImageView imageView;
    public Overdude() {
        super(2,2);
        this.image = new Image("images/overdu-de.png");
        this.imageView = new ImageView(image);
    }
    public ImageView getImageView() {
        return imageView;
    }
}
