package entities;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Notebook extends Tower {
    private Image image;
    private Image img1;
    private Image img2;
    private Image img3;
    private Image img4;
    private int frameNum = 1;
    private ImageView imageView;

    private int price = 100;
    private int upgradeCost = 200;
    private boolean upgraded = false;

    private String description;
    private Point2D pos;

    private int range;
    private int attack;
    private int cap = 3;

    private final int baseRange = 150;
    private final int baseAttack = 5;

    private final int upgradedRange = 200;
    private final int upgradedAttack = 10;

    private Color laserColor;

    public Notebook(double x, double y) {
        super();
        this.pos = new Point2D(x, y);

        this.laserColor = Color.BLUE;;
        this.attack = baseAttack;
        this.range = baseRange;

        this.image = new Image("images/notebook1.png");
        this.img1 = new Image("images/notebook1.png");
        this.img2 = new Image("images/notebook2.png");
        this.img3 = new Image("images/notebook3.png");
        this.img4 = new Image("images/notebook4.png");
        this.imageView = new ImageView(image);
        imageView.setFitWidth(super.getImageSize());
        imageView.setFitHeight(super.getImageSize());
        description = "Notebook Tower"
                + "\nCost: " + price
                + "\nRange: " + range
                + "\nAttack Dmg: " + attack
                + "\n"
                + "Upgrade Notebook Tower"
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
        enemy.setHealth(enemy.getHealth() - this.attack);
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
        return "Notebook Tower";
    }
}
