package xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Alex on 27.11.2016.
 */

@JacksonXmlRootElement(localName = "columnsName")
public class ColumnsName {

    @JacksonXmlProperty
    ObservableList<String> column = FXCollections.observableArrayList();             // массив

    public ColumnsName(ObservableList<String> column) {
        this.column = column;
    }
}
