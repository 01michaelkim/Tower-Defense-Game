package entities;

        import javafx.scene.canvas.GraphicsContext;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;

public class Benzo extends Enemy {
    private Image image;
    private ImageView imageView;
    private int cash = 100;
    private int damage = 100;
    private double transparency = 1.0;

    public Benzo(double x, double y) {
        super(5, 10, x, y);
        this.image = new Image("images/benzo.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
    }

    @Override
    public ImageView getImageView() {
        return imageView;
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
    public double getTransparency() {
        return transparency;
    }

    @Override
    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setGlobalAlpha(this.transparency);
        g.drawImage(this.image, super.getPos().getX(), super.getPos().getY());
        g.setGlobalAlpha(1);
    }
}
