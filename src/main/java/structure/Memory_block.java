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
