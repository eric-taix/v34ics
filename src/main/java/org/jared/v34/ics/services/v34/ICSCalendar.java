package org.jared.v34.ics.services.v34;

import biweekly.Biweekly;
import biweekly.ICalVersion;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.io.TimezoneAssignment;
import biweekly.io.text.ICalWriter;
import biweekly.property.Contact;
import biweekly.property.DateEnd;
import biweekly.property.Organizer;

import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.services.Calendar;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

@Service
public class ICSCalendar implements Calendar {

    private final TimezoneAssignment timeZone = new TimezoneAssignment(TimeZone.getTimeZone("Europe/Paris"), "Europe/Paris");

    @Override
    public String getCalendar(List<Event> events) throws V34Exception {

        ICalendar ical = new ICalendar();
        ical.getTimezoneInfo().setDefaultTimezone(timeZone);
        ical.addName("Nam of calendar");

        for (Event event : events) {
            VEvent vEvent = new VEvent();
            vEvent.setUid(event.getId());
            vEvent.addCategories(event.getType());

            /*VAlarm vAlarm = new VAlarm(Action.audio(), new Trigger(new Duration().hparse()))
            vEvent.addAlarm(vAlarm); */
            vEvent.setColor("YELLOW");
            vEvent.setUrl("http://wwww.volley34.fr");
            vEvent.setDateEnd(event.getEndDate(), !event.isFullDay());
            vEvent.setDateStart(event.getStartDate(), !event.isFullDay());

            vEvent.setDescription(event.getDescription());
            vEvent.setSummary(event.getName());
            vEvent.setLocation(event.getPlace());

            Organizer organizer = new Organizer(event.getContactName(), event.getContactEmail());
            organizer.setParameter("Phone", event.getContactPhone());
            organizer = new Organizer("Eric Rike", "eric.taix-docket@gmail.com");
            organizer.setParameter("Phone", "0652838477");
            vEvent.setOrganizer(organizer);

            Contact contact = new Contact("Eric Contact");
            contact.setParameter("name", "Eric Rikou");
            contact.setParameter("email", "eric.docket@gmail.com");
            contact.setParameter("phone", "0678759309");
            vEvent.addContact(contact);

            ical.addEvent(vEvent);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try(ICalWriter writer = new ICalWriter(bos, ICalVersion.V2_0))  {
            writer.write(ical);
            writer.flush();
        } catch (IOException e) {
            throw new V34Exception(e);
        }
        return new String(bos.toByteArray());
    }
}
