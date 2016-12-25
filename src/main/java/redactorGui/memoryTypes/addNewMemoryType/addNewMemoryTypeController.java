package redactorGui.memoryTypes.addNewMemoryType;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import redactorGui.memoryTypes.memoryTypeRecord;
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
    private TextField lv;

    @FXML
    private TextField pv;

    @FXML
    private TextArea commentsArea;

    @FXML
    private GridPane wagonPane;

    private Stage dialogStage;
    private memoryTypeRecord memoryRecord;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Счетчик", "Регистр", "Вагон", "Таблица"));
        typeChoiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if (typeChoiceBox.getItems().get(newValue.intValue()).equals("Вагон")) {
                nameField.setVisible(false);
                wagonPane.setVisible(true);
            } else {
                wagonPane.setVisible(false);
                nameField.setVisible(true);
            }
        });
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setRecord(memoryTypeRecord memoryRecord) {
        this.memoryRecord = memoryRecord;
        typeChoiceBox.setValue(memoryRecord.getType());
        if (typeChoiceBox.getValue().toString().equals("Вагон")) {
            lv.setText(memoryRecord.getName().split(" | ")[0]);
            pv.setText(memoryRecord.getName().split(" | ")[2]);
        } else {
            nameField.setText(memoryRecord.getName());
        }
        commentsArea.setText(memoryRecord.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if(isInputValid()) {
            memoryRecord.setType(typeChoiceBox.getValue().toString());
            memoryRecord.setComments(commentsArea.getText());
            if (memoryRecord.getType().equals("Вагон")) {
                String wagonName = lv.getText() + " | " + pv.getText();
                memoryRecord.setName(wagonName);
            } else {
                memoryRecord.setName(nameField.getText());
            }

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

        if (typeChoiceBox.getValue().toString().equals("Вагон")) {

            if (lv.getText() == null || lv.getText().length() == 0) {
                errorMessage += "Не присвоено имя левой ячейке вагонной памяти!\n";
            }

            if (pv.getText() == null || pv.getText().length() == 0) {
                errorMessage += "Не присвоено имя правой ячейке вагонной памяти!\n";
            }

        } else {

            if (nameField.getText() == null || nameField.getText().length() == 0) {
                errorMessage += "Должна быть объявлена хотя бы одна память!\n";
            }

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
