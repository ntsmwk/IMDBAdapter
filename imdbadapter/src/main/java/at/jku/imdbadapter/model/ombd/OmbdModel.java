package at.jku.imdbadapter.model.ombd;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import at.jku.imdbadapter.adpater.BooleanTypeAdapter;
import at.jku.imdbadapter.model.Model;

public abstract class OmbdModel implements Model{

    private boolean valid;

    @XmlElement(name = "Response")
    @XmlJavaTypeAdapter(BooleanTypeAdapter.class)
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean response) {
        this.valid = response;
    }
}
