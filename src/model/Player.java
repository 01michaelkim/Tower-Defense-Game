package model;

public class Player {
    private String characterName;
    private static int money = 500;
    private static int health;

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

    public static int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public static int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
