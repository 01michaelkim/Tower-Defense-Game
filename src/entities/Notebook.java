package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GameModel;

public class Notebook extends Tower {
    private Image image;
    private ImageView imageView;
    private int price;
    private String description;
    private Point2D pos;

    public Notebook(double x, double y) {
        super();
        this.image = new Image("images/notebook.png");
        this.imageView = new ImageView(image);
        this.pos = new Point2D(x,y);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        description = "Notebook Tower\n Cost: " + price;
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

    @Override
    public void attack() {
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(image, pos.getX(), pos.getY());
    }
    @Override
    public String toString() {
        return "Notebook Tower";
    }
}
