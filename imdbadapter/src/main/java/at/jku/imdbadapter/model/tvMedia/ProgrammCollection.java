package at.jku.imdbadapter.model.tvMedia;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import at.jku.imdbadapter.model.Model;

public class ProgrammCollection implements Model {

    private List<Programm> morning;
    private List<Programm> midday;
    private List<Programm> evening;
    private List<Programm> night;

    @XmlElement(name = "ts0")
    public List<Programm> getMorning() {
        return morning;
    }

    public void setMorning(List<Programm> morning) {
        this.morning = morning;
    }

    @XmlElement(name = "ts1")
    public List<Programm> getMidday() {
        return midday;
    }

    public void setMidday(List<Programm> midday) {
        this.midday = midday;
    }

    @XmlElement(name = "ts2")
    public List<Programm> getEvening() {
        return evening;
    }

    public void setEvening(List<Programm> evening) {
        this.evening = evening;
    }

    @XmlElement(name = "ts3")
    public List<Programm> getNight() {
        return night;
    }

    public void setNight(List<Programm> night) {
        this.night = night;
    }

    @Override
    public String toString() {
        return String.format("ProgrammCollection [morning=%s, midday=%s, evening=%s, night=%s]", morning, midday,
                evening, night);
    }
}
