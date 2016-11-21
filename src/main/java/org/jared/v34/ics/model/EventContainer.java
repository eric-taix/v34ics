package org.jared.v34.ics.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sp_GetAll_CalendarEventFiltered")
public class EventContainer {

    List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    @XmlElement(name = "Row")
    public EventContainer setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    public EventContainer addTeam(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        events.add(event);
        return this;
    }
}
