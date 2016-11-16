package org.jared.v34.ics.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Row")
public class Team {

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "NomEquipe")
    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    @XmlAttribute(name= "EquipeCode")
    public Team setCode(String code) {
        this.code = code;
        return this;
    }
}
