package org.jared.v34.ics.services.v34;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Contact;
import biweekly.property.DateEnd;
import biweekly.property.Organizer;

import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.services.Calendar;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICSCalendar implements Calendar {

    @Override
    public String getCalendar(String calendarName, List<Event> events) {

        ICalendar ical = new ICalendar();
        ical.addName(calendarName);
        ical.addExperimentalProperty("X-WR-TIMEZONE", "Europe/Paris");
        ical.addExperimentalProperty("X-WR-CALNAME", calendarName);
        for (Event event : events) {
            VEvent vEvent = new VEvent();
            vEvent.setUid(event.getId());
            vEvent.addCategories(event.getType());
            vEvent.setColor("YELLOW");
            vEvent.setUrl("http://wwww.volley34.fr");
            vEvent.setDateEnd(event.getEndDate(), !event.isFullDay());
            vEvent.setDateStart(event.getStartDate(), !event.isFullDay());
            vEvent.setDescription(event.getDescription());
            vEvent.setSummary(event.getName());
            vEvent.setLocation(event.getPlace());

            ical.addEvent(vEvent);
        }
        return Biweekly.write(ical).go();
    }
}
