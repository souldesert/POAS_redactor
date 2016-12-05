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
@JacksonXmlRootElement(localName = "alg")
public class Alg {

    @JacksonXmlProperty
    ObservableList<Arm> arm = FXCollections.observableArrayList();                  // массив

    public Alg() {
    }

    public void addArm(Arm arm) {
        this.arm.add(arm);
    }

    public void updateArm(Arm arm) {
        this.arm.add(this.arm.size()-1, arm);
    }

    public Arm getArm(int index) {
        return arm.get(index);
    }

}
