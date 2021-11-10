package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.ProgramScreen;

public interface ProgramScreenControllerInterface {
    public Scene getScene();
    public Stage getStage();
    public void setNextStage(ProgramScreen programScreen);
}
