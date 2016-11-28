package gui;

import gui.alphabets.alphabetRecord;
import gui.alphabets.alphabetsController;
import gui.memoryTypes.memoryTypeRecord;
import gui.memoryTypes.memoryTypesController;
import gui.redactor.Command;
import gui.redactor.addNewLine.addNewLineController;
import gui.redactor.RedactorController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {


    private Tab redactorTab;
    private Tab memoryTypesTab;
    private Tab alphabetsTab;
    private Stage primaryStage;
    private BorderPane RootWindow;
    private ObservableList<Command> commandData = FXCollections.observableArrayList();
    private ObservableList<memoryTypeRecord> memoryTypesData = FXCollections.observableArrayList();
    private ObservableList<alphabetRecord> alphabetsData = FXCollections.observableArrayList();

    public void setRedactorTab(Tab redactorTab) {
        this.redactorTab = redactorTab;
    }

    public void setMemoryTypesTab(Tab memoryTypesTab) {
        this.memoryTypesTab = memoryTypesTab;
    }

    public void setAlphabetsTab(Tab alphabetsTab) {
        this.alphabetsTab = alphabetsTab;
    }


    public MainApp() {
//        commandData.add(new Command()); // тестовая строка с командой
//        commandData.add(new Command());
//        commandData.add(new Command());
//        commandData.add(new Command());
//        commandData.add(new Command());
//        commandData.add(new Command());
    }

    public ObservableList<Command> getCommandData() {
        return commandData;
    }
    public ObservableList<memoryTypeRecord> getMemoryTypesData() { return memoryTypesData; }
    public ObservableList<alphabetRecord> getAlphabetsData() { return alphabetsData; }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("R_tran");

        initRootWindow();
        showRedactor();
        showMemoryTypes();
        showAlphabets();
        this.getPrimaryStage().show();
        //showTreeLeft();
    }

    public void initRootWindow() {
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("RootWindow.fxml"));
            RootWindow = (BorderPane) loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(RootWindow);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootWindowController controller = loader.getController();
            controller.setMainApp(this);

            //primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Redactor
    private void showRedactor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("redactor/Redactor.fxml"));
            AnchorPane redactor = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            redactorTab.setContent(redactor);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            RedactorController controller = loader.getController();
            controller.setMainApp(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMemoryTypes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("memoryTypes/memoryTypes.fxml"));
            AnchorPane memoryTypes = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            memoryTypesTab.setContent(memoryTypes);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            memoryTypesController controller = loader.getController();
            controller.setMainApp(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlphabets() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getClassLoader().getResource("alphabets/alphabets.fxml"));
            AnchorPane alphabets = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            alphabetsTab.setContent(alphabets);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            alphabetsController controller = loader.getController();
            controller.setMainApp(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }



}

