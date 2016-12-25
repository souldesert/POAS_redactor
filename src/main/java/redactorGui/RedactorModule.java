package redactorGui;

import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import org.apache.commons.lang3.StringUtils;
import redactorGui.alphabets.alphabetRecord;
import redactorGui.alphabets.alphabetsController;
import redactorGui.memoryTypes.memoryTypeRecord;
import redactorGui.memoryTypes.memoryTypesController;
import redactorGui.redactor.Command;
import redactorGui.redactor.PredicateTypes;
import redactorGui.redactor.RedactorController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import structure.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedactorModule extends Application {


    private Tab redactorTab;
    private Tab memoryTypesTab;
    private Tab alphabetsTab;
    private Stage primaryStage;
    private BorderPane RootWindow;

    public void setCommandData(ObservableList<Command> commandData) {
        this.commandData = commandData;
    }

    private ObservableList<Command> commandData = FXCollections.observableArrayList();
    private ObservableList<memoryTypeRecord> memoryTypesData = FXCollections.observableArrayList();
    private ObservableList<alphabetRecord> alphabetsData = FXCollections.observableArrayList();

    private R_pro r_pro; // в этом объекте хранится сама программа Р-тран

    public R_pro getR_pro() {
        return r_pro;
    }

    public void updateR_pro(R_pro r_pro) {
        this.r_pro = r_pro;
    }

    
    

    public void setRedactorTab(Tab redactorTab) {
        this.redactorTab = redactorTab;
    }

    public void setMemoryTypesTab(Tab memoryTypesTab) {
        this.memoryTypesTab = memoryTypesTab;
    }

    public void setAlphabetsTab(Tab alphabetsTab) {
        this.alphabetsTab = alphabetsTab;
    }
    
    


    public RedactorModule() {
        r_pro = new R_pro();
        r_pro.setProgname("Без названия");
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
        //this.primaryStage.setTitle("R_tran");

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
            loader.setLocation(RedactorModule.class.getClassLoader().getResource("RedactorWindow.fxml"));
            RootWindow = (BorderPane) loader.load();
            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(RootWindow);
            primaryStage.setScene(scene);
            primaryStage.setTitle(r_pro.getProgname());

            // Give the controller access to the main app.
            RedactorModuleController controller = loader.getController();
            controller.setRedactorModule(this);

            //primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Redactor
    private void showRedactor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RedactorModule.class.getClassLoader().getResource("redactor/Redactor.fxml"));
            AnchorPane redactor = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            redactorTab.setContent(redactor);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            RedactorController controller = loader.getController();
            controller.setRedactorModule(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMemoryTypes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RedactorModule.class.getClassLoader().getResource("memoryTypes/memoryTypes.fxml"));
            AnchorPane memoryTypes = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            memoryTypesTab.setContent(memoryTypes);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            memoryTypesController controller = loader.getController();
            controller.setRedactorModule(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlphabets() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RedactorModule.class.getClassLoader().getResource("alphabets/alphabets.fxml"));
            AnchorPane alphabets = (AnchorPane) loader.load();
            // Помещаем сведения об адресатах в центр корневого макета.
            alphabetsTab.setContent(alphabets);
            //Scene scene = new Scene(redactor);
            //primaryStage.setScene(scene);


            alphabetsController controller = loader.getController();
            controller.setRedactorModule(this);

            //primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Memory> getMemory() {
        ObservableList<Memory> memory = FXCollections.observableArrayList();
        for (memoryTypeRecord record : memoryTypesData) {

            switch (record.getType()) {
                case "Счетчик":
                    memory.add(new Memory("Counter", record.getName()));
                    break;
                case "Регистр":
                    memory.add(new Memory("Register", record.getName()));
                    break;
                case "Вагон":
                    String lv = record.getName().split(" | ")[0];
                    String pv = record.getName().split(" | ")[2];
                    memory.add(new Memory("Wagon", lv, pv));
                    break;
                case "Таблица":
                    memory.add(new Memory("Table", record.getName()));
                    break;
            }

        }
        return memory;
    }

    public ObservableList<Abc> getAbcs() {
        ObservableList<Abc> abcs = FXCollections.observableArrayList();
        for(alphabetRecord record : alphabetsData) {
            abcs.add(new Abc(record.getName(), record.getShortName(), record.getComments(), record.getValues()));
        }
        return abcs;
    }

    public Descriptive_part getDescriptive_part() {
        Memory_block memory_block = new Memory_block(getMemory());
        Alphabet alphabet = new Alphabet(getAbcs());
        Descriptive_part descriptive_part = new Descriptive_part(memory_block, alphabet);
        return descriptive_part;
    }

    // TODO: 26.12.2016 Решить вопрос с пробелами при разборе линопа - в текущей версии все они удаляются

    private Operation getOperation(Command record) {
        String linop = record.getLinop();
        Left left = new Left();
        String operator = "";
        Right right = new Right();
        Operation operation;
        List<String> knownOperators = new ArrayList<>();
        knownOperators.add("=");
        knownOperators.add("->");
        knownOperators.add("<-");
        for(String oper : knownOperators) {
            if (linop.contains(oper)) {
                String stripped = StringUtils.remove(linop, " ");
                String[] tokens = stripped.split(oper);
                left = new Left(tokens[0]);
                operator = oper;
                right = new Right(tokens[1]);
            }
        }
        operation = new Operation(left, operator, right);
        return operation;
    }

    public Alg getAlg() {
        Alg alg = new Alg();
        int curArm = -1;
        int curEdge = 0;
        for (Command record : commandData) {
            String type;
            Predicate predicate;
            switch (record.getFlag()) {
                case TAG:

//                    Left left = new Left(record.getLinop().split(" ")[0]);
//                    String operator = record.getLinop().split(" ")[1];
//                    Right right = new Right(record.getLinop().split(" ")[2]);
//                    Operation operation = new Operation(left, operator, right);

                    Operation operation = getOperation(record);

                    switch (record.getPredicateType()) {
                        case ALPHABET:
                            type = "alphabet";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                        case EXPRESSION:
                            type = "expression";
                            Memory memoryLeft = new Memory(record.getUslovie().split(" ")[0]);
                            String sign = record.getUslovie().split(" ")[1];
                            Memory memoryRight = new Memory(record.getUslovie().split(" ")[2]);
                            predicate = new Predicate(type, memoryLeft, sign, memoryRight);
                            break;
                        case MEMORY:
                            type = "memory";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                        default:
                            type = "string";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                    }

                    curEdge = 0;

                    Edge edge = new Edge(predicate, operation);
                    edge.addEnd(record.getMetkaPerehoda());

                    Arm arm = new Arm(record.getMetka());
                    arm.addEdge(edge);
                    alg.addArm(arm);
                    curArm++;

                    System.out.println(record.getFlag() + ". Записано: " + curArm + curEdge + " " + record.getLinop());

                    break;

                case CONDITION:

//                    left = new Left(record.getLinop().split(" ")[0]);
//                    operator = record.getLinop().split(" ")[1];
//                    right = new Right(record.getLinop().split(" ")[2]);
//                    operation = new Operation(left, operator, right);

                    operation = getOperation(record);

                    switch (record.getPredicateType()) {
                        case ALPHABET:
                            type = "alphabet";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                        case EXPRESSION:
                            type = "expression";
                            Memory memoryLeft = new Memory(record.getUslovie().split(" ")[0]);
                            String sign = record.getUslovie().split(" ")[1];
                            Memory memoryRight = new Memory(record.getUslovie().split(" ")[2]);
                            predicate = new Predicate(type, memoryLeft, sign, memoryRight);
                            break;
                        case MEMORY:
                            type = "memory";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                        default:
                            type = "string";
                            predicate = new Predicate(type, record.getUslovie());
                            break;
                    }

                    curEdge++;

                    edge = new Edge(predicate, operation);
                    edge.addEnd(record.getMetkaPerehoda());

                    alg.getArm(curArm).addEdge(edge);

                    System.out.println(record.getFlag() + ". Записано: " + curArm + curEdge + " " + record.getLinop());

//                    Arm currentArm = alg.getArm(curArm);
//                    currentArm.addEdge(edge);
//                    alg.updateArm(currentArm);

                    break;

                case OPERATOR:

//                    left = new Left(record.getLinop().split(" ")[0]);
//                    operator = record.getLinop().split(" ")[1];
//                    right = new Right(record.getLinop().split(" ")[2]);
//                    operation = new Operation(left, operator, right);

                    operation = getOperation(record);

                    alg.getArm(curArm).getEdge(curEdge).addOperation(operation);
                    alg.getArm(curArm).getEdge(curEdge).addEnd(record.getMetkaPerehoda());

                    break;
            }
        }
        return alg;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }



}

