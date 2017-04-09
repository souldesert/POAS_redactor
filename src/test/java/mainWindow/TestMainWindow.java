package mainWindow;

import com.google.common.io.Resources;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import redactorGui.RedactorModule;

import java.io.File;

/**
 * Created by Alex on 03.04.2017.
 * Пример простого приложения, в которое встраивается RedactorModule
 */

public class TestMainWindow extends Application {

    private Stage primaryStage;
    private BorderPane borderPane;

    private Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        /**
         * Создание и инициализация редактора (с загрузкой из файла)
         */

        AnchorPane redactorPane = new AnchorPane();

        RedactorModule redactorModule = new RedactorModule();
        File rTranProgram = new File(Resources.getResource("empty.rtran").getFile());
        redactorModule.init(redactorPane, rTranProgram);

        /**
         * Создание вкладок снизу
         */

        TabPane bottom = new TabPane();
        bottom.getTabs().addAll(createBottomTabs());

        /**
         * Создание дерева слева
         */

        TreeView<Label> left = new TreeView<>();
        TreeItem<Label> item = new TreeItem<>();
        item.setValue(new Label("Четыре"));
        left.setRoot(item);


        borderPane = new BorderPane();
        borderPane.setCenter(redactorPane);
        borderPane.setBottom(bottom);
        borderPane.setLeft(left);

        Scene scene = new Scene(borderPane);
        this.getPrimaryStage().setScene(scene);
        this.getPrimaryStage().show();

        //redactorModule.load(new File(Resources.getResource("empty.rtran").getFile()));

        //redactorModule.save();
    }

    private ObservableList<Tab> createBottomTabs() {
        Tab one = new Tab("Раз");
        Tab two = new Tab("Два");
        Tab three = new Tab("Три");
        one.setContent(new TextArea());
        two.setContent(new TextArea());
        three.setContent(new TextArea());
        ObservableList<Tab> tabs = FXCollections.observableArrayList();
        tabs.add(one);
        tabs.add(two);
        tabs.add(three);
        return tabs;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
