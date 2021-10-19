package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public class Plant extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;

    public Plant() {
        this.image = new Image("images/plant.png");
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
        description = "Plant Tower\n Cost: " + price;
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
}
