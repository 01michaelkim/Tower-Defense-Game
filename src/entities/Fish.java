package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Fish extends Tower {
    private Image image;
    private Image img1;
    private Image img2;
    private Image img3;
    private Image img4;
    private int frameNum = 1;
    private ImageView imageView;

    private int price = 150;
    private int upgradeCost = 300;
    private boolean upgraded = false;

    private String description;
    private Point2D pos;

    private int range;
    private int attack;
    private int cap = 2;

    private final int baseRange = 200;
    private final int baseAttack = 10;

    private final int upgradedRange = 250;
    private final int upgradedAttack = 20;

    private Color laserColor;

    public Fish(double x, double y) {
        super();
        this.pos = new Point2D(x, y);

        this.laserColor = Color.PURPLE;
        this.attack = baseAttack;
        this.range = baseRange;

        this.image = new Image("images/fish1.png");
        this.img1 = new Image("images/fish1.png");
        this.img2 = new Image("images/fish2.png");
        this.img3 = new Image("images/fish3.png");
        this.img4 = new Image("images/fish4.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Fish Tower"
                + "\nCost: " + price
                + "\nRange: " + range
                + "\nAttack Dmg: " + attack
                + "\n"
                + "Upgrade Fish Tower"
                + "\nCost: " + price
                + "\nRange: " + upgradedRange
                + "\nAttack Dmg: " + upgradedAttack;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public boolean upgraded() {
        return upgraded;
    }

    @Override
    public void upgradeTower() {
        this.laserColor = Color.GOLD;
        this.upgraded = true;
        this.attack = upgradedAttack;
        this.range = upgradedRange;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public Point2D getPos() {
        return this.pos;
    }

    @Override
    public ImageView getImageView() {
        return imageView;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int getUpgradeCost() {
        return upgradeCost;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.drawImage(this.image, this.pos.getX(), this.pos.getY());
    }

    @Override
    public void attack(Enemy enemy) {
        enemy.takeDamage(this.attack);
    }

    @Override
    public void drawLaser(GraphicsContext g, Tower tower, Enemy enemy) {
        g.setFill(laserColor);
        g.setStroke(laserColor);
        g.setLineWidth(2);
        g.beginPath();
        g.moveTo(tower.getPos().getX() + 16, tower.getPos().getY() + 16);
        g.stroke();
        g.lineTo(enemy.getPos().getX() + 16, enemy.getPos().getY() + 16);
        g.stroke();
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
    @Override
    public int getCap() {
        return cap;
    }
    @Override
    public String toString() {
        return "Fish Tower";
    }
}
