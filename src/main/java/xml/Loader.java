package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import structure.R_pro;

import java.io.*;

/**
 * Created by Alex on 28.11.2016.
 */
public class Loader {

    public R_pro getReaded() {
        return readed;
    }

    R_pro readed;

    public Loader() {
    }

    public boolean load() {
        Stage loadStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть программу");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Программа Р-тран", "*.rtran"));
        File destination = fileChooser.showOpenDialog(loadStage);
        if (destination != null) {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            try {
                BufferedReader read = new BufferedReader(new FileReader(destination));
                String program = read.readLine();
                readed = xmlMapper.readValue(program, R_pro.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }

}
