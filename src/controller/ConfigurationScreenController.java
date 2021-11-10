package controller;

import javafx.scene.control.*;
import model.GameModel;
import model.Player;
import view.ConfigurationScreen;
import view.GameScreen;

public class ConfigurationScreenController extends ProgramScreenController {
    private TextField nameLabel;
    private Label namePrompt;
    private ComboBox dropdown;
    private Button playButton;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

    public ConfigurationScreenController(ConfigurationScreen configurationScreen) {
        this.dropdown = configurationScreen.getDropdown();
        this.nameLabel = configurationScreen.getNameLabel();
        this.namePrompt = configurationScreen.getNamePrompt();
        this.playButton = configurationScreen.getPlayButton();
        this.currentStage = configurationScreen.getStage();
    }

    public boolean checkName(TextField name) {
        if (name.getText().isEmpty()) {
            this.alert.setTitle("Error!");
            this.alert.setHeaderText("ERROR WITH NAME");
            this.alert.setContentText("You MUST enter a name that isn't empty!");
            this.alert.getDialogPane().getStylesheets().add("resources/SceneStyle.css");
            alert.showAndWait();
            return false;
        } else if (name.getText().trim().length() == 0) {
            this.alert.setTitle("Error!");
            this.alert.setHeaderText("ERROR WITH NAME");
            this.alert.setContentText("You MUST enter a name with valid characters!");
            this.alert.getDialogPane().getStylesheets().add("resources/SceneStyle.css");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public boolean checkDifficulty() {
        if (dropdown.getValue() != null) {
            return true;
        } else {
            this.alert.setTitle("Error!");
            this.alert.setHeaderText("ERROR WITH DIFFICULTY");
            this.alert.setContentText("You MUST enter a difficulty!");
            this.alert.getDialogPane().getStylesheets().add("resources/SceneStyle.css");
            alert.showAndWait();
            return false;
        }
    }

    public void playButtonHandler() {
        this.playButton.setOnAction(e -> {
            if (this.checkName(this.nameLabel) && this.checkDifficulty()) {
                GameModel.setDifficulty(dropdown.getValue().toString());

                Player player = new Player(GameModel.getDifficulty());
                player.setCharacterName(nameLabel.getText());

                setNextStage(new GameScreen(player));
                currentStage.close();
                currentStage = null;
                currentStage = nextStage;
                currentStage.show();
            }
        });
    }
}
