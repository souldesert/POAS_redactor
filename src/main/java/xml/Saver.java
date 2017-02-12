package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import structure.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Alex on 27.11.2016.
 */
public class Saver {

    public File getKnownDestination() {
        return knownDestination;
    }

    public void setKnownDestination(File destination) {
        this.knownDestination = destination;
    }

    private File knownDestination;

    public boolean saveAs(R_pro r_pro) {
        Stage saveStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
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
    
    public void save(R_pro r_pro) {
        String progname = StringUtils.removeEnd(knownDestination.getName(), ".rtran");
        r_pro.setProgname(progname);
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);
        try {
            xmlMapper.writeValue(knownDestination, r_pro);
            System.out.println("Сохранено успешно!");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
        }
    }
    
}
