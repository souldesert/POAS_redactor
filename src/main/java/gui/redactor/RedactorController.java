package gui.redactor;

import gui.MainApp;
import gui.redactor.addNewLine.addNewLineController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by svkreml on 23.10.2016.
 */
public class RedactorController {

    //private Stage addStage;

    @FXML
    private TableView<Command> commandTable;

    @FXML
    private TableColumn<Command, String> metkaColumn;

    @FXML
    private TableColumn<Command, String> uslovieColumn;

    @FXML
    private TableColumn<Command, String> linopColumn;

    @FXML
    private TableColumn<Command, String> metkaPerehodaColumn;

    @FXML
    private TableColumn<Command, String> commentsColumn;

    private MainApp mainApp;

    public RedactorController() {
    }

    @FXML
    private void initialize() {
        metkaColumn.setCellValueFactory(cellData -> cellData.getValue().metkaProperty());
        uslovieColumn.setCellValueFactory(cellData -> cellData.getValue().uslovieProperty());
        linopColumn.setCellValueFactory(cellData -> cellData.getValue().linopProperty());
        metkaPerehodaColumn.setCellValueFactory(cellData -> cellData.getValue().metkaPerehodaProperty());
        commentsColumn.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
    }

    private boolean showCommandEditDialog(Command command) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("redactor/addNewLine/addNewLine.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование команды");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            addNewLineController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCommand(command);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleDeleteCommand() {
        int selectedIndex = commandTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            commandTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не была выбрана ни одна команда");
            alert.setContentText("Пожалуйста, выберите команду в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddNewLineCommand() {
        Command tempCommand = new Command();
        //Command selectedCommand = commandTable.getSelectionModel().getSelectedItem();
        boolean okClicked = showCommandEditDialog(tempCommand);
        if(okClicked) {
            mainApp.getCommandData().add(tempCommand);
        }
    }

    @FXML
    private void handleEditCommand() {

        Command selectedCommand = commandTable.getSelectionModel().getSelectedItem();
        showCommandEditDialog(selectedCommand);

    }


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        commandTable.setItems(mainApp.getCommandData());
    }
}
