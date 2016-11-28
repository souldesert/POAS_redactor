package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sun.xml.internal.bind.v2.TODO;
import gui.alphabets.alphabetRecord;
import gui.memoryTypes.memoryTypeRecord;
import gui.redactor.Command;
import gui.redactor.Flags;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.rmi.runtime.NewThreadAction;

import java.io.File;
import java.io.IOException;

import static gui.redactor.Flags.CONDITION;

/**
 * Created by Alex on 27.11.2016.
 */
public class Saver {

    private ObservableList<Command> commandData;
    private ObservableList<memoryTypeRecord> memoryTypesData;
    private ObservableList<alphabetRecord> alphabetsData;

    public Saver(ObservableList<Command> commandData, ObservableList<memoryTypeRecord> memoryTypesData,
                 ObservableList<alphabetRecord> alphabetsData) {

        this.commandData = commandData;
        this.memoryTypesData = memoryTypesData;
        this.alphabetsData = alphabetsData;

    }

    public void save() {
        R_pro r_pro = new R_pro("1.0", "Test", getDescriptive_part(), getAlg());
        File destination = new File("test.xml");
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        try {
            //xmlMapper.addMixIn()
            xmlMapper.writeValue(destination, r_pro);
            System.out.println("Сохранено успешно!");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }


    public ObservableList<Memory> getMemory() {
        ObservableList<Memory> memory = FXCollections.observableArrayList();
        for (memoryTypeRecord record : memoryTypesData) {
            memory.add(new Memory(record.getType(), record.getName()));
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


    // TODO: 27.11.2016 сделать разбор операции 
    // TODO: 27.11.2016 сделать другие типы предикатов кроме expression
    public Alg getAlg() {
        Alg alg = new Alg();
        int curArm = -1;
        int curEdge = 0;
        for (Command record : commandData) {
            switch (record.getFlag()) {
                case TAG:

                    Left left = new Left(record.getLinop().split(" ")[0]);
                    String operator = record.getLinop().split(" ")[1];
                    Right right = new Right(record.getLinop().split(" ")[2]);
                    Operation operation = new Operation(left, operator, right);

                    String type = "expression";
                    Memory memoryLeft = new Memory(record.getUslovie().split(" ")[0]);
                    String sign = record.getUslovie().split(" ")[1];
                    Memory memoryRight = new Memory(record.getUslovie().split(" ")[2]);
                    Predicate predicate = new Predicate(type, memoryLeft, sign, memoryRight);

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

                    left = new Left(record.getLinop().split(" ")[0]);
                    operator = record.getLinop().split(" ")[1];
                    right = new Right(record.getLinop().split(" ")[2]);
                    operation = new Operation(left, operator, right);

                    type = "expression";
                    memoryLeft = new Memory(record.getUslovie().split(" ")[0]);
                    sign = record.getUslovie().split(" ")[1];
                    memoryRight = new Memory(record.getUslovie().split(" ")[2]);
                    predicate = new Predicate(type, memoryLeft, sign, memoryRight);

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

                    left = new Left(record.getLinop().split(" ")[0]);
                    operator = record.getLinop().split(" ")[1];
                    right = new Right(record.getLinop().split(" ")[2]);
                    operation = new Operation(left, operator, right);

                    alg.getArm(curArm).getEdge(curEdge).addOperation(operation);
                    alg.getArm(curArm).getEdge(curEdge).addEnd(record.getMetkaPerehoda());

                    System.out.println(record.getFlag() + ". Записано: " + curArm + curEdge + " " + record.getLinop());
                    break;
            }
        }
        return alg;
    }

}
