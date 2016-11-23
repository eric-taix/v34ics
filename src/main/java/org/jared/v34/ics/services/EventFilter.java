package org.jared.v34.ics.services;

import org.jared.v34.ics.model.Event;

public interface EventFilter {

    boolean accept(Event event);

}
