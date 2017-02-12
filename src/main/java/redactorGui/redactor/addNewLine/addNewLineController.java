package redactorGui.redactor.addNewLine;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import redactorGui.RedactorModule;
import redactorGui.alphabets.alphabetRecord;
import redactorGui.memoryTypes.memoryTypeRecord;
import redactorGui.redactor.Command;
import redactorGui.redactor.Flags;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;
import redactorGui.redactor.PredicateTypes;

/**
 * Created by Alex on 24.10.2016.
 */
public class addNewLineController {

    private ObservableList<String> predicateOptions = FXCollections.observableArrayList();
    private ObservableList<String> memoryOptions = FXCollections.observableArrayList();
    private ObservableList<String> alphabetOptions = FXCollections.observableArrayList();
    private ObservableList<String> operatorOptions = FXCollections.observableArrayList();
    private ObservableList<String> funcOptions = FXCollections.observableArrayList();

    private ObservableList<String> metkaPerehodaOptions = FXCollections.observableArrayList();

    @FXML
    private TextField metkaField;

    @FXML
    private ComboBox uslovieField;

    @FXML
    private ComboBox linopFieldLeft;

    @FXML
    private ComboBox linopFieldCenter;

    @FXML
    private ComboBox linopFieldRight;


    @FXML
    private ComboBox metkaPerehodaField;

    @FXML
    private TextArea commentsArea;

    private Stage dialogStage;
    private Command command;
    private boolean okClicked = false;
    private RedactorModule redactorModule;

    public void loadOptions() {

        // Загрузить опции для поля "Условие"

        for (memoryTypeRecord record : redactorModule.getMemoryTypesData()) {
            if (record.getType().equals("Вагон")) {
                memoryOptions.add(record.getName().split(" | ")[0]);
                memoryOptions.add(record.getName().split(" | ")[2]);
            } else {
                memoryOptions.add(record.getName());
            }
        }
        for (alphabetRecord record : redactorModule.getAlphabetsData()) {
            alphabetOptions.add(record.getName());
        }

        predicateOptions.addAll(memoryOptions);
        predicateOptions.addAll(alphabetOptions);
        uslovieField.setItems(predicateOptions);
        TextFields.bindAutoCompletion(uslovieField.getEditor(), uslovieField.getItems());

        // Загрузить опции для поля "Метка перехода"

        for (Command record : redactorModule.getCommandData()) {
            metkaPerehodaOptions.add(record.getMetka());
        }

        metkaPerehodaField.setItems(metkaPerehodaOptions);
        TextFields.bindAutoCompletion(metkaPerehodaField.getEditor(), metkaPerehodaField.getItems());

        // Загрузить опции линейных операторов


        linopFieldLeft.setItems(memoryOptions);
        TextFields.bindAutoCompletion(linopFieldLeft.getEditor(), linopFieldLeft.getItems());

        operatorOptions.add("|-");
        operatorOptions.add("-|");
        operatorOptions.add("&=");
        operatorOptions.add("~=");
        operatorOptions.add("^");
        operatorOptions.add("^=");
        operatorOptions.add(":");
        operatorOptions.add(":=");
        operatorOptions.add("->");
        operatorOptions.add("<-");
        operatorOptions.add("/");
        operatorOptions.add("*");

        linopFieldCenter.setItems(operatorOptions);
        TextFields.bindAutoCompletion(linopFieldCenter.getEditor(), linopFieldCenter.getItems());

        funcOptions.add("sqrt()");

        linopFieldRight.setItems(funcOptions);
        TextFields.bindAutoCompletion(linopFieldRight.getEditor(), linopFieldRight.getItems());
    }

    @FXML
    private void initialize() {
    }


    public void setRedactorModule(RedactorModule redactorModule) {
        this.redactorModule = redactorModule;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCommand(Command command) {
        this.command = command;
        metkaField.setText(command.getMetka());
        uslovieField.setValue(command.getUslovie());
        // todo заменить
        if (command.getLinop().equals("*")) {
            linopFieldCenter.setValue("*");
        } else if (command.getLinop().length() != 0) {
            linopFieldLeft.setValue(command.getLinop().split(" ")[0]);
            linopFieldCenter.setValue(command.getLinop().split(" ")[1]);
            linopFieldRight.setValue(command.getLinop().split(" ")[2]);
        }

        metkaPerehodaField.setValue(command.getMetkaPerehoda());
        commentsArea.setText(command.getComments());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if(isInputValid()) {
            command.setMetka(metkaField.getText());
            command.setUslovie(uslovieField.getValue().toString());
            // todo заменить
            // System.out.println(linopField.getText());
            if (linopFieldCenter.getValue().toString().equals("*")) {
                command.setLinop(linopFieldCenter.getValue().toString());
            } else {
                command.setLinop(linopFieldLeft.getValue().toString() + " " + linopFieldCenter.getValue().toString()
                        + " " + linopFieldRight.getValue().toString());
            }
            command.setMetkaPerehoda(metkaPerehodaField.getValue().toString());
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
        boolean uslovieNotEmpty = !(uslovieField.getValue().toString() == null || uslovieField.getValue().toString().length() == 0);
        // todo заменить
        boolean linopLeftNotEmpty = !(linopFieldLeft.getValue() == null || linopFieldLeft.getValue().toString().length() == 0);
        boolean linopCenterNotEmpty = !(linopFieldCenter.getValue() == null || linopFieldCenter.getValue().toString().length() == 0);
        boolean linopRightNotEmpty = !(linopFieldRight.getValue() == null || linopFieldRight.getValue().toString().length() == 0);
        boolean linopIsOkay = (linopLeftNotEmpty && linopCenterNotEmpty && linopRightNotEmpty) || linopFieldCenter.getValue().toString().equals("*");

        if (linopIsOkay) {
            command.setFlag(Flags.OPERATOR);
            if (uslovieNotEmpty) {
                command.setFlag(Flags.CONDITION);
                command.setPredicateType(determinePredicateType());
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
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Информация о команде");
//            alert.setHeaderText("Флаг: ");
//            alert.setContentText(command.getFlag().toString());
//            alert.showAndWait();
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

    private PredicateTypes determinePredicateType() {
        String value = uslovieField.getValue().toString();
        if (memoryOptions.contains(value)) {
            return PredicateTypes.MEMORY;
        } else if (alphabetOptions.contains(value)) {
            return PredicateTypes.ALPHABET;
        } else if (value.contains(">") || value.contains("<") || value.contains("=")) {
            return PredicateTypes.EXPRESSION;
        } else {
            return PredicateTypes.STRING;
        }
    }

}
