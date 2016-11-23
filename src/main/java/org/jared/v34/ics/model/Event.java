package org.jared.v34.ics.model;

import org.jared.v34.ics.utils.V34BooleanXmlAdapter;
import org.jared.v34.ics.utils.V34DateXmlAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "Row")
public class Event {

    private String contactEmail;
    private String contactPhone;
    private String contactName;
    private String description;
    private String place;
    private Boolean fullDay;
    private Date endDate;
    private Date startDate;
    private String type;
    private String name;
    private String id;


    public String getContactEmail() {
        return contactEmail;
    }

    @XmlAttribute(name = "CalendarEventContactEmail")
    public Event setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
        return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    @XmlAttribute(name = "CalendarEventContactPhone")
    public Event setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public String getContactName() {
        return contactName;
    }

    @XmlAttribute(name = "CalendarEventContactName")
    public Event setContactName(String contactName) {
        this.contactName = contactName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    @XmlAttribute(name = "CalendarEvenDescription")
    public Event setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPlace() {
        return place;
    }

    @XmlAttribute(name = "CalendarEventPlace")
    public Event setPlace(String place) {
        this.place = place;
        return this;
    }

    public Boolean isFullDay() {
        return fullDay;
    }

    @XmlAttribute(name = "CalendarEventFullDay")
    @XmlJavaTypeAdapter(V34BooleanXmlAdapter.class)
    public Event setFullDay(Boolean fullDay) {
        this.fullDay = fullDay;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    @XmlAttribute(name = "CalendarEventEndDate")
    @XmlJavaTypeAdapter(V34DateXmlAdapter.class)
    public Event setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

    @XmlAttribute(name = "CalendarEventStartDate")
    @XmlJavaTypeAdapter(V34DateXmlAdapter.class)
    public Event setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getType() {
        return type;
    }

    @XmlAttribute(name = "CalendarEventType")
    public Event setType(String type) {
        this.type = type;
        return this;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute(name = "CalendarEventName")
    public Event setName(String name) {
        this.name = name;
        return this;
    }

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "CalendarEventID")
    public Event setId(String id) {
        this.id = id;
        return this;
    }
    /*

-- Réunion
    CalendarEventContactEmail=""
    CalendarEventContactPhone=""
    CalendarEventContactName=""
    CalendarEventImageURL=""
    CalendarEventDesciption=""
    CalendarEventPlace="Maison Départementale des Sports Nelson MANDELA (Pierres Vives)"
    CalendarEventFullDay="False"
    CalendarEventEndDate="21/09/2016 23:00:00"
    CalendarEventStartDate="21/09/2016 20:00:00"
    CalendarEventType="R"
    CalendarEventName="A.G. Commission Volley-ball"
    CalendarEventID="2017-CE00010"


-- Match

    CalendarEventContactEmail=""
    CalendarEventContactPhone=""
    CalendarEventContactName=""
    CalendarEventImageURL=""
    CalendarEventDesciption=""
    CalendarEventPlace="Salle Polyvalente (Vailhauquès)"
    CalendarEventFullDay="False"
    CalendarEventEndDate="15/11/2016 22:30:00"
    CalendarEventStartDate="15/11/2016 20:30:00"
    CalendarEventType="M"
    CalendarEventName="VCV/Les Tatouilles reçoit OLA Pignan 4x4 Mixte "
    CalendarEventID="2017-CE00273"


-- Tournoi

    CalendarEventContactEmail=""
    CalendarEventContactPhone=""
    CalendarEventContactName=""
    CalendarEventImageURL=""
    CalendarEventDesciption="4 x 4 Mixtes "
    CalendarEventPlace="Halle Patrice CANAYER (Gigean)"
    CalendarEventFullDay="False"
    CalendarEventEndDate="16/10/2016 03:00:00"
    CalendarEventStartDate="15/10/2016 19:00:00"
    CalendarEventType="T"
    CalendarEventName="Tournoi Nocturne de Gigean"
    CalendarEventID="2017-CE00011"

-- Federal

    CalendarEventContactEmail=""
    CalendarEventContactPhone=""
    CalendarEventContactName=""
    CalendarEventPrivate="False"
    CalendarEventImageURL=""
    CalendarEventDesciption=""
    CalendarEventPlace="En fonction des poules"
    CalendarEventFullDay="True"
    CalendarEventEndDate="21/01/2017 00:00:00"
    CalendarEventStartDate="21/01/2017 00:00:00"
    CalendarEventType="F"
    CalendarEventName="Fédéraux FSGT 6x6 - 1er Tour"
    CalendarEventID="2017-CE00019"


     */
}
