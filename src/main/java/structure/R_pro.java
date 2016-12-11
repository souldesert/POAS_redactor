package structure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Created by Alex on 27.11.2016.
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@JacksonXmlRootElement(localName = "r-pro")
public class R_pro {

    @JacksonXmlProperty(isAttribute = true)
    String version;

    @JacksonXmlProperty
    String progname;

    @JacksonXmlProperty
    String comment = "";

    @JacksonXmlProperty
    Descriptive_part descriptive_part;

    public Alg getAlg() {
        return alg;
    }

    public void setAlg(Alg alg) {
        this.alg = alg;
    }

    @JacksonXmlProperty
    Alg alg;

    public R_pro(String version, String progname, Descriptive_part descriptive_part, Alg alg) {
        this.version = version;
        this.descriptive_part = descriptive_part;
        this.alg = alg;
        this.progname = progname;
    }

    public String getProgname() {
        return progname;
    }

    public void setProgname(String progname) {
        this.progname = progname;
    }

//    public R_pro(String version, String progname, String comment, Descriptive_part descriptive_part, Alg alg) {
//        this.version = version;
//        this.progname = progname;
//        this.comment = comment;
//        this.descriptive_part = descriptive_part;
//        this.alg = alg;
//    }


    // TEST
//    public R_pro(String version, String progname, Descriptive_part descriptive_part) {
//        this.version = version;
//        this.progname = progname;
//        this.descriptive_part = descriptive_part;
//    }

    public R_pro() {
        progname = "Без названия";
    }

}
