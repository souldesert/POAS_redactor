package structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Alex on 27.11.2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "edge")
public class Edge {

    @JacksonXmlProperty(isAttribute = true)
    String end;

    @JacksonXmlProperty
    Predicate predicate;

    @JacksonXmlProperty
    ObservableList<Operation> operation = FXCollections.observableArrayList();     // массив

    public Edge(Predicate predicate, Operation operation) {
        this.predicate = predicate;
        this.operation.add(operation);
    }

    public void addOperation(Operation operation) {
        this.operation.add(operation);
    }

    public void addEnd(String end) {
        this.end = end;
    }
}
