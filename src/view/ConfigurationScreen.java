package view;

import controller.ConfigurationScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;

public class ConfigurationScreen extends ProgramScreen {
    private final int width = 500;
    private final int height = 250;
    private Button playButton;
    private ComboBox dropdown;
    private TextField nameLabel;
    private static Label namePrompt;
    private VBox configBox = new VBox();
    private ConfigurationScreenController controller;

    public ConfigurationScreen() {
        this.initStage(this.width, this.height);
        this.initStageElements();
        this.initController();
    }

    @Override
    public void initController() {
        this.controller = new ConfigurationScreenController(this);
        this.startController();
    }

    public void startController() {
        this.currentStage.show();
        this.controller.playButtonHandler();
    }

    public void initStageElements() {
        this.createTitle();
        this.createNameInput();
        this.createDropdownMenu();
        this.createPlayButtonPane();
        this.createStage();
    }

    public void createTitle() {
        Label title = new Label("Set Game Configurations!");
        HBox titleBox = new HBox(title);
        titleBox.setAlignment(Pos.TOP_CENTER);
        this.configBox.getChildren().add(titleBox);
    }

    public void createNameInput() {
        namePrompt = new Label("Enter your name:");
        nameLabel = new TextField();
        HBox nameBox = new HBox(namePrompt, nameLabel);
        nameBox.setSpacing(10);
        nameBox.setAlignment(Pos.CENTER);
        this.configBox.getChildren().add(nameBox);
    }

    public void createDropdownMenu() {
        dropdown = new ComboBox();
        Label label = new Label("Select the Difficulty:");
        dropdown.getItems().addAll(
                "EASY",
                "MEDIUM",
                "HARD"
        );
        dropdown.setPromptText("Select Level");
        VBox difficultyBox = new VBox(label, dropdown);
        difficultyBox.setAlignment(Pos.CENTER);
        this.configBox.getChildren().add(difficultyBox);
    }

    public void createPlayButtonPane() {
        playButton = new Button("Play Game");
        HBox playBox = new HBox(playButton);
        playBox.setAlignment(Pos.CENTER);
        this.configBox.getChildren().add(playBox);
    }

    public void createStage() {
        this.configBox.setAlignment(Pos.CENTER);
        this.configBox.setSpacing(25);
        this.configBox.setPadding(new Insets(5, 5, 5, 5));
        this.pane.setCenter(configBox);
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
