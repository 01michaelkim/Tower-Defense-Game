package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Plant extends Tower {
    private Image image;
    private ImageView imageView;

    private int price = 50;
    private int upgradeCost = 100;
    private boolean upgraded = false;

    private String description;
    private Point2D pos;

    private int range;
    private int attack;

    private final int baseRange = 100;
    private final int baseAttack = 2;

    private final int upgradedRange = 150;
    private final int upgradedAttack = 4;

    private Color laserColor;

    public Plant(double x, double y) {
        super();
        this.pos = new Point2D(x, y);

        this.laserColor = Color.LIGHTGREEN;;
        this.attack = baseAttack;
        this.range = baseRange;

        this.image = new Image("images/plant.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Plant Tower"
                + "\nCost: " + price
                + "\nRange: " + baseRange
                + "\nAttack Dmg: " + baseAttack
                + "\n"
                + "Upgrade Plant Tower"
                + "\nCost: " + price
                + "\nRange: " + upgradedRange
                + "\nAttack Dmg: " + upgradedAttack;;
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

    @Override
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
    public String toString() {
        return "Plant Tower";
    }
}
