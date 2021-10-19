package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public class Fish extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;

    public Fish() {
        this.image = new Image("images/fish.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        if (GameModel.getDifficulty() == "EASY") {
            price = 50;
        }
        if (GameModel.getDifficulty() == "MEDIUM") {
            price = 100;
        }
        if (GameModel.getDifficulty() == "HARD") {
            price = 150;
        }
        description = "Fish Tower\n Cost: " + price;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
