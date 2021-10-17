package entities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public class Notebook extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;

    public Notebook() {
        this.image = new Image("images/notebook.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        if (GameModel.getDifficulty() == "EASY") {
            price = 50;
        }
        if (GameModel.getDifficulty() == "MEDIUM") {
            price = 100;
        }
        if (GameModel.getDifficulty() == "HARD"){
            price = 150;
        }
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
