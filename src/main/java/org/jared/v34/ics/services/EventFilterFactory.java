package org.jared.v34.ics.services;

import org.jared.v34.ics.model.Event;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventFilterFactory {

    public List<Event> filter(List<Event> events, String type) {
        List<EventFilter> filters = createFilters(type);
        return events.stream().filter((event) ->
                filters.stream().anyMatch((f) ->
                        f.accept(event))).collect(Collectors.toList());
    }

    private List<EventFilter> createFilters(String type) {
        List<EventFilter> filters = new ArrayList<>();
        for (int iLoop = 0; iLoop < type.length(); iLoop++) {
            filters.add(new TypeEventFilter(""+type.charAt(iLoop)));
        }
        return filters;
    }

}
