package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

public interface ProgramScreenInterface {
    public Scene getScene();

    public Stage getStage();

    public void initStage(int width, int height);

    public void initController();
}
