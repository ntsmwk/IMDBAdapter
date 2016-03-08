package at.jku.imdbadapter.adpater;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import at.jku.imdbadapter.model.ombd.Type;

public class TypeTypeAdapter extends XmlAdapter<String, Type> {

    private static final String N_A = "N/A";

    @Override
    public String marshal(Type v) throws Exception {
        return v.name().toLowerCase();
    }

    @Override
    public Type unmarshal(String v) throws Exception {
        if (v.equals(N_A)) {
            return null;
        }
        return Type.valueOf(v.toUpperCase());
    }

}
