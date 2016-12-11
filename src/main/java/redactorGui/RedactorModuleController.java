package redactorGui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import redactorGui.alphabets.alphabetsController;
import redactorGui.memoryTypes.memoryTypesController;
import redactorGui.redactor.Command;
import redactorGui.redactor.Flags;
import redactorGui.redactor.RedactorController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import structure.Arm;
import structure.Edge;
import structure.Operation;
import structure.R_pro;
import xml.Loader;
import xml.Saver;

import java.util.List;

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
        R_pro readed = loader.getReaded();
        redactorModule.updateR_pro(readed);
        ObservableList<Command> readedCommands = FXCollections.observableArrayList();
        Command currentCommand = new Command();
        for (Arm arm : readed.getAlg().getArm()) {      // <- Почему не работает перебор массива??? Arm : ObservableList<Arm>
            currentCommand.setMetka(arm.getBegin());
            currentCommand.setFlag(Flags.TAG);
            for (Edge edge : arm.getEdge()) {
                String memoryLeft = edge.getPredicate().getMemoryLeft().getName();
                String memoryRight = edge.getPredicate().getMemoryRight().getName();
                String sign = edge.getPredicate().getSign();
                currentCommand.setUslovie(memoryLeft + " " + sign + " " + memoryRight);
                if (currentCommand.getFlag() != Flags.TAG) currentCommand.setFlag(Flags.CONDITION);
                for (Operation operation : edge.getOperation()) {
                    String left = operation.getLeft().getValue();
                    String right = operation.getRight().getValue();
                    String operator = operation.getOperator();
                    currentCommand.setLinop(left + " " + operator + " " + right);
                    if (currentCommand.getFlag() != Flags.TAG && currentCommand.getFlag() != Flags.CONDITION)
                        currentCommand.setFlag(Flags.OPERATOR);
                    readedCommands.add(currentCommand);
                    currentCommand = new Command();

                }
            }
//            readedCommands.add(currentCommand);
//            currentCommand = new Command();
        }
        redactorModule.getCommandData().removeAll();
        redactorModule.getCommandData().addAll(readedCommands);
    }

    /**
     * Закрывает приложение.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }



}
