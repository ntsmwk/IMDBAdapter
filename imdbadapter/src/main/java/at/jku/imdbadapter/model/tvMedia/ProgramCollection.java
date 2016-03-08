package at.jku.imdbadapter.model.tvMedia;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import at.jku.imdbadapter.model.Model;

public class ProgramCollection implements Model {

    private List<Program> morning;
    private List<Program> midday;
    private List<Program> evening;
    private List<Program> night;

    @XmlElement(name = "ts0")
    public List<Program> getMorning() {
        return morning;
    }

    public void setMorning(List<Program> morning) {
        this.morning = morning;
    }

    @XmlElement(name = "ts1")
    public List<Program> getMidday() {
        return midday;
    }

    public void setMidday(List<Program> midday) {
        this.midday = midday;
    }

    @XmlElement(name = "ts2")
    public List<Program> getEvening() {
        return evening;
    }

    public void setEvening(List<Program> evening) {
        this.evening = evening;
    }

    @XmlElement(name = "ts3")
    public List<Program> getNight() {
        return night;
    }

    public void setNight(List<Program> night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return String.format("ProgrammCollection [morning=%s, midday=%s, evening=%s, night=%s]", morning, midday,
                evening, night);
    }
}
