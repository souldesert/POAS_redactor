package structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.immutables.value.Value;

import java.util.List;

/**
 * Created by Alex on 27.11.2016.
 */

//@Value.Immutable
@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "arm")
public class Arm {

    public String getBegin() {
        return begin;
    }

    @JacksonXmlProperty(isAttribute = true)
    String begin;

    public ObservableList<Edge> getEdge() {
        return edge;
    }

    @JacksonXmlProperty
    ObservableList<Edge> edge = FXCollections.observableArrayList();         // массив

    public Arm(String begin) {
        this.begin = begin;
    }

    public void setEdge(List edge) {
        this.edge.addAll(edge);
    }

    public void addEdge(Edge edge) {
        this.edge.add(edge);
    }

    public void updateEdge(Edge edge) {
        this.edge.add(this.edge.size()-1, edge);
    }

    public Edge getEdge(int index) {
        return edge.get(index);
    }

}
