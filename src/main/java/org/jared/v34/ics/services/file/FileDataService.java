package org.jared.v34.ics.services.file;

import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.model.EventContainer;
import org.jared.v34.ics.model.Team;
import org.jared.v34.ics.model.TeamContainer;
import org.jared.v34.ics.services.DataService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Service
@Profile("no_network")
public class FileDataService implements DataService {

    private static final String TEAM_FILE = "/Users/Eric/Documents/perso/v34ics/src/main/resources/equipes-test.xml";
    private static final String CALENDAR_FILE = "/Users/Eric/Documents/perso/v34ics/src/main/resources/calendar-test.xml";

    @Override
    public List<Team> getTeams() throws V34Exception {
        try {
            FileInputStream fis = new FileInputStream(TEAM_FILE);
            JAXBContext context = JAXBContext.newInstance(TeamContainer.class);

            Unmarshaller um = context.createUnmarshaller();
            TeamContainer tc = (TeamContainer) um.unmarshal(fis);
            return tc.getTeams();
        } catch (IOException | JAXBException ex) {
            throw new V34Exception();
        }

    }

    @Override
    public List<Event> getAllEvents(String team) throws V34Exception {
        try {
            FileInputStream fis = new FileInputStream(CALENDAR_FILE);
            JAXBContext context = JAXBContext.newInstance(EventContainer.class);

            Unmarshaller um = context.createUnmarshaller();
            EventContainer ec = (EventContainer) um.unmarshal(fis);
            return ec.getEvents();
        } catch (IOException | JAXBException ex) {
            throw new V34Exception(ex);
        }
    }

}
