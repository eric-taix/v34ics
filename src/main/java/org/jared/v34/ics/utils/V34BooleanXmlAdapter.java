package org.jared.v34.ics.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class V34BooleanXmlAdapter extends XmlAdapter<String, Boolean> {
    @Override
    public Boolean unmarshal(String v) throws Exception {
        return (v != null && v.equals("True"));
    }

    @Override
    public String marshal(Boolean v) throws Exception {
        return (v ? "True" : "False");
    }
}
