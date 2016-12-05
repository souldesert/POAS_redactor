package redactorGui.redactor.addNewLine;

import redactorGui.redactor.Command;
import redactorGui.redactor.Flags;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Alex on 24.10.2016.
 */
public class addNewLineController {

    @FXML
    private TextField metkaField;

    @FXML
    private TextField uslovieField;

    @FXML
    private TextField linopField;

    @FXML
    private TextField metkaPerehodaField;

    @FXML
    private TextArea commentsArea;

    private Stage dialogStage;
    private Command command;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCommand(Command command) {
        this.command = command;
        metkaField.setText(command.getMetka());
        uslovieField.setText(command.getUslovie());
        linopField.setText(command.getLinop());
        metkaPerehodaField.setText(command.getMetkaPerehoda());
        commentsArea.setText(command.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            command.setMetka(metkaField.getText());
            command.setUslovie(uslovieField.getText());
            command.setLinop(linopField.getText());
            command.setMetkaPerehoda(metkaPerehodaField.getText());
            command.setComments(commentsArea.getText());
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

        boolean metkaNotEmpty = !(metkaField.getText() == null || metkaField.getText().length() == 0);
        boolean uslovieNotEmpty = !(uslovieField.getText() == null || uslovieField.getText().length() == 0);
        boolean linopNotEmpty = !(linopField.getText() == null || linopField.getText().length() == 0);

        if (linopNotEmpty) {
            command.setFlag(Flags.OPERATOR);
            if (uslovieNotEmpty) {
                command.setFlag(Flags.CONDITION);
                if (metkaNotEmpty) {
                    command.setFlag(Flags.TAG);
                }
            } else if (metkaNotEmpty) {
                errorMessage += "Не заполнено поле 'Условие'!\n";
            }
        } else {
            errorMessage += "Не заполнено поле 'Линейный оператор'!\n";
        }

        if(errorMessage.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Информация о команде");
            alert.setHeaderText("Флаг: ");
            alert.setContentText(command.getFlag().toString());
            alert.showAndWait();
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Поле не заполнено");
            alert.setHeaderText("Пожалуйста, заполните поле");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

}
