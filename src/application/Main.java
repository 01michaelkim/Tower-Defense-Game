package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ProgramScreen;
import view.WelcomeScreen;
 
public class Main extends Application {
    private ProgramScreen screen;
    @Override
    public void start(Stage primaryStage) {
        try {
            WelcomeScreen screen = new WelcomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProgramScreen getScreen() {
        return screen;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
