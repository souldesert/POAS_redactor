package xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sun.javafx.geom.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Alex on 27.11.2016.
 */

@JacksonXmlRootElement(localName = "arm")
public class Arm {

    @JacksonXmlProperty(isAttribute = true)
    String begin;

    @JacksonXmlProperty
    ObservableList<Edge> edge = FXCollections.observableArrayList();         // массив

    public Arm(String begin) {
        this.begin = begin;

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
