package at.jku.imdbadapter.adpater;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanTypeAdapter extends XmlAdapter<String, Boolean> {


    @Override
    public String marshal(Boolean v) throws Exception {
        return toBoolean(v.toString());
    }

    @Override
    public Boolean unmarshal(String v) throws Exception {
        return Boolean.valueOf(v.toLowerCase());
    }

    private String toBoolean(String value) {
        String prefix = value.substring(0, 1).toUpperCase();
        String postfix = value.substring(1).toLowerCase();
        return prefix + postfix;
    }
}
