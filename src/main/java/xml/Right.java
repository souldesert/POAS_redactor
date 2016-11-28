package xml;

/**
 * Created by Alex on 27.11.2016.
 */

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "right")
public class Right {

    @JacksonXmlProperty(isAttribute = true)
    String value;

    public Right(String value) {
        this.value = value;
    }
}
