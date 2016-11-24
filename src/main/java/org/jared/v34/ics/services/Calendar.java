package org.jared.v34.ics.services;

import org.jared.v34.ics.model.Event;

import java.util.List;

public interface Calendar {

    String getCalendar(String calendarName, List<Event> events);
}
