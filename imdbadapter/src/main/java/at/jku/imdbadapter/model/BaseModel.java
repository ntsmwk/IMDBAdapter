package at.jku.imdbadapter.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import at.jku.imdbadapter.adpater.BooleanTypeAdapter;

public abstract class BaseModel {

    private boolean response;

    @XmlElement(name = "Response")
    @XmlJavaTypeAdapter(BooleanTypeAdapter.class)
    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

}
