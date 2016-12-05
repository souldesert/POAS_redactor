package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structure.R_pro;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 28.11.2016.
 */
public class Loader {

    R_pro r_pro;

    public Loader() {
        r_pro = new R_pro();
    }

    public void load() {
        Stage loadStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть программу");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Программа Р-тран", "*.rtran"));
        File destination = fileChooser.showOpenDialog(loadStage);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        try {

            xmlMapper.readValue(destination, R_pro.class);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }

        //System.out.println(r_pro.progname);

    }

}
