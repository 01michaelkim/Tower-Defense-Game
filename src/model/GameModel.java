package model;

public class GameModel {
    private static String difficulty;
    private static boolean gameClosed = false;
    private static int numdead;

    public static int getNumdead() {
        return numdead;
    }
    public static void setNumdead(int numdeadd) {
        numdead = numdeadd;
    }
    public static void setDifficulty(String inputDifficulty) {
        difficulty = inputDifficulty;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setGameClosed() {
        gameClosed = true;
    }

    public static boolean getGameClosed() {
        return gameClosed;
    }



}
