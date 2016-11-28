package gui.alphabets;

import gui.MainApp;
import gui.alphabets.addNewAlphabet.addNewAlphabetController;
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
 * Created by Alex on 20.11.2016.
 */
public class alphabetsController {

    @FXML
    private TableView<alphabetRecord> alphabetsTable;

    @FXML
    private TableColumn<alphabetRecord, String> syntermColumn;

    @FXML
    private TableColumn<alphabetRecord, String> nameColumn;

    @FXML
    private TableColumn<alphabetRecord, String> shortNameColumn;

    @FXML
    private TableColumn<alphabetRecord, String> valuesColumn;

    @FXML
    private TableColumn<alphabetRecord, String> commentsColumn;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        syntermColumn.setCellValueFactory(cellData -> cellData.getValue().syntermProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        shortNameColumn.setCellValueFactory(cellData -> cellData.getValue().shortNameProperty());
        valuesColumn.setCellValueFactory(cellData -> cellData.getValue().valuesProperty());
        commentsColumn.setCellValueFactory(cellData -> cellData.getValue().commentsProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        alphabetsTable.setItems(mainApp.getAlphabetsData());
    }

    private boolean showAlphabetEditDialog(alphabetRecord alphabet) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("alphabets/addNewAlphabet/addNewAlphabet.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Редактирование синтерма");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            addNewAlphabetController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRecord(alphabet);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void handleDeleteRecord() {
        int selectedIndex = alphabetsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            alphabetsTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Не была выбрана ни одна запись");
            alert.setContentText("Пожалуйста, выберите запись в таблице");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddNewAlphabet() {
        alphabetRecord tempRecord = new alphabetRecord();
        //Command selectedCommand = commandTable.getSelectionModel().getSelectedItem();
        boolean okClicked = showAlphabetEditDialog(tempRecord);
        if(okClicked) {
            mainApp.getAlphabetsData().add(tempRecord);
        }
    }

    @FXML
    private void handleEditRecord() {

        alphabetRecord selectedRecord = alphabetsTable.getSelectionModel().getSelectedItem();
        showAlphabetEditDialog(selectedRecord);

    }
    
}
