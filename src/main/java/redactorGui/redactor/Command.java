package redactorGui.redactor;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Alex on 23.10.2016.
 */


@XmlRootElement
public class Command {

    private final StringProperty metka;
    private final StringProperty uslovie;
    private final StringProperty linop;
    private final StringProperty metkaPerehoda;
    private final StringProperty comments;
    private Flags flag;

    /**
     * Конструктор по умолчанию.
     */
    public Command() {
        this.metka = new SimpleStringProperty("");
        this.uslovie = new SimpleStringProperty("");
        this.linop = new SimpleStringProperty("");
        this.metkaPerehoda = new SimpleStringProperty("");
        this.comments = new SimpleStringProperty("");

    }

    public Command(String metka, String uslovie, String linop, String metkaPerehoda, String comments, Flags flag) {
        this.metka = new SimpleStringProperty(metka);
        this.uslovie = new SimpleStringProperty(uslovie);
        this.linop = new SimpleStringProperty(linop);
        this.metkaPerehoda = new SimpleStringProperty(metkaPerehoda);
        this.comments = new SimpleStringProperty(comments);
        this.flag = flag;
    }

    public String getMetka() {
        return metka.get();
    }

    public void setMetka(String metka) {
        this.metka.set(metka);
    }

    public StringProperty metkaProperty() {
        return metka;
    }

    // -------------------------------------------------------------------

    public String getUslovie() {
        return uslovie.get();
    }

    public void setUslovie(String uslovie) {
        this.uslovie.set(uslovie);
    }

    public StringProperty uslovieProperty() {
        return uslovie;
    }

    // -------------------------------------------------------------------

    public String getLinop() {
        return linop.get();
    }

    public void setLinop(String linop) {
        this.linop.set(linop);
    }

    public StringProperty linopProperty() {
        return linop;
    }

    // -------------------------------------------------------------------

    public String getMetkaPerehoda() {
        return metkaPerehoda.get();
    }

    public void setMetkaPerehoda(String metkaPerehoda) {
        this.metkaPerehoda.set(metkaPerehoda);
    }

    public StringProperty metkaPerehodaProperty() {
        return metkaPerehoda;
    }

    // -------------------------------------------------------------------


    public String getComments() {
        return comments.get();
    }

    public void setComments(String comments) {
        this.comments.set(comments);
    }

    public StringProperty commentsProperty() {
        return comments;
    }

    // -------------------------------------------------------------------


    public Flags getFlag() { return flag; }

    public void setFlag(Flags newFlag) { this.flag = newFlag; }

    //@XmlJavaTypeAdapter(LocalDateAdapter.class)
}
