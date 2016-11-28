package gui.memoryTypes.addNewMemoryType;

import gui.memoryTypes.memoryTypeRecord;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Alex on 21.11.2016.
 */
public class addNewMemoryTypeController {

    @FXML
    private ChoiceBox typeChoiceBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea commentsArea;

    private Stage dialogStage;
    private memoryTypeRecord memoryRecord;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("счетчик", "регистр", "вагон", "таблица"));
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRecord(memoryTypeRecord memoryRecord) {
        this.memoryRecord = memoryRecord;
        typeChoiceBox.setValue(memoryRecord.getType());
        nameField.setText(memoryRecord.getName());
        commentsArea.setText(memoryRecord.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            memoryRecord.setType(typeChoiceBox.getValue().toString());
            memoryRecord.setName(nameField.getText());
            memoryRecord.setComments(commentsArea.getText());
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

        if (typeChoiceBox.getValue() == null || typeChoiceBox.getValue().toString().length() == 0) {
            errorMessage += "Выберите тип памяти!\n";
        }

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "Должна быть объявлена хотя бы одна память!\n";
        }

        if(errorMessage.length() == 0) {
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
