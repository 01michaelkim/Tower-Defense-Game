package view;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

public class ConfigurationScreen {
    private int width;
    private int height;
    private Button playButton;
    private Button easyButton;
    private Button medButton;
    private Button hardButton;
    private String mode;
    private TextField tf;
    private String name;

    public ConfigurationScreen(int width, int height) {
        this.width = width;
        this.height = height;
        playButton = new Button("Play Game");
        easyButton = new Button("Easy");
        medButton = new Button("Medium");
        hardButton = new Button("Hard");
        mode = "Medium";
        tf = new TextField();
        name = "";
    }

    public Scene getScene() {
        //Screen Title
        Label title = new Label("Configurations");
        HBox titleBox = new HBox(title);
        titleBox.setPadding(new Insets(10, 5, 10, 180));

        //Name Label
        Label namePrompt = new Label("Enter your name:");
        Label nameLabel = new Label(name);
        HBox nameBox = new HBox(namePrompt, nameLabel);
        nameBox.setPadding(new Insets(10, 5, 10, 180));

        //Text Field
        HBox txtBox = new HBox(tf);
        txtBox.setPadding(new Insets(10, 5, 10, 150));
        //Difficulty Selection Buttons
        HBox diffButtons = new HBox(easyButton, medButton, hardButton);
        diffButtons.setPadding(new Insets(50, 5, 10, 150));

        //Difficulty Label
        HBox diff = new HBox();
        Label label = new Label("Select the Difficulty:");
        String difficulty[] = {"EASY", "MEDIUM", "HARD"};
        ComboBox combo = new ComboBox(FXCollections.observableArrayList(difficulty));
        combo.setPromptText("Select Level");
        diff.getChildren().addAll(label, combo);
        diff.setPadding(new Insets(10, 5, 10, 150));



        //Play Button
        HBox playBox = new HBox(playButton);
        playBox.setPadding(new Insets(50, 5, 5, 190));

        //Root Pane
        VBox vbox = new VBox(titleBox, nameBox, txtBox, diff, playBox);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public Button getStartButton() {
        return playButton;
    }
    public void setName() {
        name = tf.getText();
        //nameLabel.setText(name);
        tf.clear();
    }
}
