package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.WelcomeScreen;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            WelcomeScreen screen = new WelcomeScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
