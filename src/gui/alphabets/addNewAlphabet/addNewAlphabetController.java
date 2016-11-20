package gui.alphabets.addNewAlphabet;

import gui.alphabets.alphabetRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Alex on 21.11.2016.
 */
public class addNewAlphabetController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField shortNameField;

    @FXML
    private TextField valuesField;

    @FXML
    private TextArea commentsArea;

    private Stage dialogStage;
    private alphabetRecord alphabet;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRecord(alphabetRecord alphabet) {
        this.alphabet = alphabet;
        nameField.setText(alphabet.getName());
        shortNameField.setText(alphabet.getShortName());
        valuesField.setText(alphabet.getValues());
        commentsArea.setText(alphabet.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            alphabet.setName(nameField.getText());
            alphabet.setShortName(shortNameField.getText());
            alphabet.setValues(valuesField.getText());
            alphabet.setComments(commentsArea.getText());
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    @FXML
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Укажите название синтерма!\n";
        }

        if (valuesField.getText() == null || valuesField.getText().length() == 0) {
            errorMessage += "Укажите набор символов!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Поля не заполнены");
            alert.setHeaderText("Пожалуйста, исправьте следующие ошибки:");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }

    }

}
