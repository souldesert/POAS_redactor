package structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Alex on 27.11.2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "predicate")
public class Predicate {


    @JacksonXmlProperty(isAttribute = true)
    String type;

    @JacksonXmlProperty(isAttribute = true)
    String name;

    @JacksonXmlProperty(isAttribute = true)
    String value;

    public Memory getMemoryLeft() {
        return memoryLeft;
    }

    public String getSign() {
        return sign;
    }

    public Memory getMemoryRight() {
        return memoryRight;
    }

    @JacksonXmlProperty/*(localName = "memory")*/
    Memory memoryLeft;

    @JacksonXmlProperty
    String sign;

    @JacksonXmlProperty/*(localName = "memory")*/
    Memory memoryRight;

    public Predicate(String type, String nameOrValue, boolean setValue) {
        this.type = type;
        if (setValue) {
            this.value = nameOrValue;
        } else {
            this.name = nameOrValue;
        }
    }

    public Predicate(String type, Memory memoryLeft, String sign, Memory memoryRight) {
        this.type = type;
        this.memoryLeft = memoryLeft;
        this.sign = sign;
        this.memoryRight = memoryRight;
    }


}
