package model;

public class Player {
    private String characterName;
    private int money = 500;
    private int health;

    public Player(String difficulty) {
        if (difficulty.equals("EASY")) {
            this.health = 300;
        } else if (difficulty.equals("MEDIUM")) {
            this.health = 200;
        } else if (difficulty.equals("HARD")) {
            this.health = 100;
        }
    }

    public void reset() {
        this.money = 500;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
