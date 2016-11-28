package gui;

import java.io.File;

import gui.alphabets.alphabetsController;
import gui.memoryTypes.memoryTypesController;
import gui.redactor.RedactorController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.stage.FileChooser;
import xml.Saver;

/**
 * Created by svkreml on 23.10.2016.
 */
public class RootWindowController {
    private MainApp mainApp;

    @FXML
    private Tab redactorTab;

    @FXML
    private Tab memoryTypesTab;

    @FXML
    private Tab alphabetsTab;

//    @FXML
//    private MenuItem save;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.mainApp.setRedactorTab(redactorTab);
        this.mainApp.setMemoryTypesTab(memoryTypesTab);
        this.mainApp.setAlphabetsTab(alphabetsTab);
    }

    @FXML
    private void initialize() {
        RedactorController command_redactor = new RedactorController();
        //redactor.setContent();
        memoryTypesController memoryTypes = new memoryTypesController();
        alphabetsController alphabets = new alphabetsController();
    }


    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(";;;");
        alert.setHeaderText("About");
        alert.setContentText("Author: ;;;");

        alert.showAndWait();
    }

    @FXML
    private void handleSave() {
        Saver saver = new Saver(mainApp.getCommandData(), mainApp.getMemoryTypesData(), mainApp.getAlphabetsData());
        saver.save();
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

}
