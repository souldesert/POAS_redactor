package structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Alex on 27.11.2016.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
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
