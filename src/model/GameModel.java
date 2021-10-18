package model;

public class GameModel {
    private static String characterName;
    private static String difficulty;
    private static int money = 500;
    private static int health;

    public static void setDifficulty(String inputDifficulty) {
        difficulty = inputDifficulty;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setMoney(int inputMoney) {
        money = inputMoney;
    }

    public static int getMoney() {
        return money;
    }

    public static void setHealth(int inputHealth) {
        health = inputHealth;
    }

    public static int getHealth() {
        return health;
    }

    public static void setCharacterName(String inputCharacterName) {
        characterName = inputCharacterName;
    }

    public static String getCharacterName() {
        return characterName;
    }
}
