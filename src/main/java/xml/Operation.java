package xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Alex on 27.11.2016.
 */

@JacksonXmlRootElement(localName = "operation")
public class Operation {

    @JacksonXmlProperty
    Left left;

    @JacksonXmlProperty
    String operator;

    @JacksonXmlProperty
    Right right;

    public Operation(Left left, String operator, Right right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
