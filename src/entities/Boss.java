package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Boss extends Enemy{
    private Image image;
    private Image img1;
    private Image img2;
    private Image img3;
    private Image img4;
    private int frameNum = 1;
    private ImageView imageView;
    private int cash = 500;
    private int damage = 500;

    public Boss(double x, double y) {
        super(80, 5, x, y);
        this.image = new Image("images/overdude1.png");
        this.img1 = new Image("images/overdude1.png");
        this.img2 = new Image("images/overdude2.png");
        this.img3 = new Image("images/overdude3.png");
        this.img4 = new Image("images/overdude4.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
    }

    @Override
    public int getCash() {
        return cash;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setGlobalAlpha(super.getTransparency());
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
        g.setGlobalAlpha(1);
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }
    @Override
    public void toggle() {
        switch(frameNum) {
            case 1:
                image = img2;
                frameNum = 2;
                break;
            case 2:
                image = img3;
                frameNum = 3;
                break;
            case 3:
                image = img4;
                frameNum = 4;
                break;
            case 4:
                image = img1;
                frameNum = 1;
                break;
            default:
                break;
        }
    }
}
