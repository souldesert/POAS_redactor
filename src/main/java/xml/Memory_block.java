package xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Alex on 27.11.2016.
 */

@JacksonXmlRootElement(localName = "memory_block")
public class Memory_block {

    @JacksonXmlProperty
    ObservableList<Memory> memory = FXCollections.observableArrayList();           // массив

    public Memory_block() {
    }

    public Memory_block(ObservableList<Memory> memory) {
        this.memory = memory;
    }
}
