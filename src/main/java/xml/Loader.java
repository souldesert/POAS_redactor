package xml;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.control.Alert;
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

    private File location;

    public File getLocation() {
        return location;
    }

    public void setLocation(File location) {
        this.location = location;
    }

    public Loader() {
    }

    public boolean load(File location) {
//        Stage loadStage = new Stage();
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
//        fileChooser.setTitle("Открыть программу");
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Программа Р-тран", "*.rtran"));
        //File destination = fileChooser.showOpenDialog(loadStage);
        this.location = location;
        if (location != null) {
            JacksonXmlModule module = new JacksonXmlModule();
            module.setDefaultUseWrapper(false);
            XmlMapper xmlMapper = new XmlMapper(module);
            try {
                BufferedReader read = new BufferedReader(new FileReader(location));
                String program = read.readLine();
                readed = xmlMapper.readValue(program, R_pro.class);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } catch (NullPointerException e) {
                Alert emptyFileErrorWindow = new Alert(Alert.AlertType.ERROR);
                emptyFileErrorWindow.setTitle("Ошибка открытия файла");
                emptyFileErrorWindow.setHeaderText("При открытии файла произошла ошибка!");
                emptyFileErrorWindow.setContentText("Файл пуст!");
                emptyFileErrorWindow.showAndWait();
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

}
