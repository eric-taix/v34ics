package org.jared.v34.ics.services;

import org.jared.v34.ics.model.Event;

public class TypeEventFilter implements EventFilter {

    private String eventType;

    public TypeEventFilter(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean accept(Event event) {
        return event.getType() != null && event.getType().equalsIgnoreCase(eventType);
    }
}
