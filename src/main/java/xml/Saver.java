package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import structure.*;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 27.11.2016.
 */
public class Saver {

    // TODO: 04.12.2016 разобраться с Exception'ами при отмене сохранения
    public boolean save(R_pro r_pro) {
        Stage saveStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Сохранить программу");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Программа Р-тран", "*.rtran"));
        File destination = fileChooser.showSaveDialog(saveStage);
        if (destination != null) {
            String progname = StringUtils.removeEnd(destination.getName(), ".rtran");
            r_pro.setProgname(progname);
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            try {
                xmlMapper.writeValue(destination, r_pro);
                System.out.println("Сохранено успешно!");
            } catch (IOException e) {
                System.out.println("I/O Error: " + e);
            }
            return true;
        } else {
            return false;
        }
    }
}
