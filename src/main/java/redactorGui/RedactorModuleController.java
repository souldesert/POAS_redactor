package redactorGui;

import redactorGui.alphabets.alphabetsController;
import redactorGui.memoryTypes.memoryTypesController;
import redactorGui.redactor.RedactorController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import xml.Loader;
import xml.Saver;

/**
 * Created by svkreml on 23.10.2016.
 */
public class RedactorModuleController {
    private RedactorModule redactorModule;

    @FXML
    private Tab redactorTab;

    @FXML
    private Tab memoryTypesTab;

    @FXML
    private Tab alphabetsTab;


    public void setRedactorModule(RedactorModule redactorModule) {
        this.redactorModule = redactorModule;
        this.redactorModule.setRedactorTab(redactorTab);
        this.redactorModule.setMemoryTypesTab(memoryTypesTab);
        this.redactorModule.setAlphabetsTab(alphabetsTab);
    }

    @FXML
    private void initialize() {
        RedactorController command_redactor = new RedactorController();
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
        Saver saver = new Saver();
        saver.save(redactorModule.getR_pro());
        redactorModule.getPrimaryStage().setTitle(redactorModule.getR_pro().getProgname());
    }

    @FXML
    private void handleLoad() {
        Loader loader = new Loader();
        loader.load();
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }



}
