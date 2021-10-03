package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import model.GameModel;

public class ConfigurationScreen {
    private int width;
    private int height;
    private Button playButton;
    private ComboBox dropdown;
    private TextField nameLabel;
    private GameModel gameModel;
    private static Label namePrompt;

    public ConfigurationScreen(int width, int height) {
        this.width = width;
        this.height = height;
        this.playButton = new Button("Play Game");
        this.gameModel = new GameModel();
        this.dropdown = new ComboBox();
        this.nameLabel = new TextField();
    }

    public Scene getScene() {
        // Create a HBOx to store the title for the Configuration screen and center the box
        Label title = new Label("Set Game Configurations");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.TOP_CENTER);

        // Create a HBox to request player name and provide a textfield for input and center the box
        namePrompt = new Label("Enter your name:");
        HBox nameBox = new HBox(namePrompt, nameLabel);
        nameBox.setSpacing(10);
        nameBox.setAlignment(Pos.CENTER);

        // Create a dropdown menu for the user to select a difficulty and center the HBox
        Label label = new Label("Select the Difficulty:");
        dropdown.getItems().addAll(
                "EASY",
                "MEDIUM",
                "HARD"
        );
        dropdown.setPromptText("Select Level");
        HBox difficultyBox = new HBox(label, dropdown);
        difficultyBox.setAlignment(Pos.CENTER);

        // Create a play button
        HBox playBox = new HBox(playButton);
        playBox.setAlignment(Pos.CENTER);

        //Updates name prompt to show the name that was entered in
        nameLabel.setOnAction(e -> {
            System.out.println(nameLabel.getText());
            checkName(nameLabel.getText());
        });

        // Create a Root Pane to add all the panes
        VBox vbox = new VBox(titleBox, nameBox, difficultyBox, playBox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(25);
        vbox.setPadding(new Insets(5, 5, 5, 5));
        Scene scene = new Scene(vbox, width, height);
        return scene;
    }

    public void checkName (String s) {
        if (s.equals(null)) {
            namePrompt.setText("You MUST enter a name!");
        } else if (s.isEmpty()) {
            namePrompt.setText("You MUST enter a name that isn't empty!");
        } else if (s.trim().length() == 0) {
            namePrompt.setText("You MUST enter a name with valid characters!");
        } else {
            namePrompt.setText("Name: " + nameLabel.getText());
        }
    }

    public Button getPlayButton() {
        return playButton;
    }

    public ComboBox getDropdown() {
        return dropdown;
    }
    public TextField getNameLabel() {
        return nameLabel;
    }

    public static Label getNamePrompt() {
        return namePrompt;
    }
}
