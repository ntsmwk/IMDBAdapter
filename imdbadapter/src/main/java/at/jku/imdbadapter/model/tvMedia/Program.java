package at.jku.imdbadapter.model.tvMedia;

import javax.xml.bind.annotation.XmlElement;

import at.jku.imdbadapter.model.Model;

public class Program implements Model {
    private String id;
    private String title;
    private String subtitle;
    private String sender;
    private String date;
    private String categorie;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "titel")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    @XmlElement(name = "subtitel")
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @XmlElement(name = "datum")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlElement(name = "kategorie")
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @XmlElement(name = "zeit")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("Programm [title=%s, time=%s, sender=%s]", title, time, sender);
    }
}
