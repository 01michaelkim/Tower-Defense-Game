package view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;

public class ProgramScreen implements ProgramScreenInterface {
    protected BorderPane pane;
    protected Scene currentScene;
    protected Stage currentStage;
    private Player player;

    public ProgramScreen() {
        this.player = null;
    }

    public void initStage(int width, int height) {
        this.pane = new BorderPane();
        this.currentScene = new Scene(this.pane, width, height);
        this.currentScene.getStylesheets().add("resources/SceneStyle.css");
        this.currentStage = new Stage();
        this.currentStage.setScene(this.currentScene);
    }

    public void initController() {

    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Scene getScene() {
        return this.currentScene;
    }

    public Stage getStage() {
        return this.currentStage;
    }
}
