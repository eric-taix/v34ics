package org.jared.v34.ics.services;

import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.model.Team;

import java.util.List;

public interface DataService {

    List<Team> getTeams() throws V34Exception;

    List<Event> getAllEvents(String team) throws V34Exception;

}
