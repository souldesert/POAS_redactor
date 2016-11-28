package xml;

/**
 * Created by Alex on 27.11.2016.
 */

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "left")
public class Left {

    @JacksonXmlProperty(isAttribute = true)
    String value;

    public Left(String value) {
        this.value = value;
    }
}
