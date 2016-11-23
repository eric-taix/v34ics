package org.jared.v34.ics.services.v34;

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import org.jared.v34.ics.exception.V34Exception;
import org.jared.v34.ics.model.Event;
import org.jared.v34.ics.model.EventContainer;
import org.jared.v34.ics.model.Team;
import org.jared.v34.ics.model.TeamContainer;
import org.jared.v34.ics.services.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@Service
@Profile("!no_network")
public class V34DataService implements DataService {

    private static final Logger LOG = LoggerFactory.getLogger(V34DataService.class);

    private static final String V34_URL = "http://webservices.volley34.fr";
    private static final String GET_TEAM = "/wsEquipes.asmx/GetEquipesCompetition";
    private static final String GET_CALENDAR = "/wsCalendriers.asmx/GetCalendrier";

    @Override
    public List<Team> getTeams() throws V34Exception {
        try {
            byte[] response = new ApacheRequest(V34_URL + GET_TEAM)
                    .method(Request.GET)
                    .fetch()
                    .binary();
            JAXBContext context = JAXBContext.newInstance(TeamContainer.class);

            Unmarshaller um = context.createUnmarshaller();
            TeamContainer tc = (TeamContainer) um.unmarshal(new ByteArrayInputStream(response));
            return tc.getTeams();
        } catch (IOException | JAXBException ex) {
            LOG.error("Error while retrieving Volley34 informations", ex);
            throw new V34Exception();
        }
    }

    @Override
    public List<Event> getAllEvents(String team) throws V34Exception {
        try {
            byte[] response = new ApacheRequest(V34_URL + GET_CALENDAR)
                    .uri()
                        .queryParam("equipeCode", team)
                        .queryParam("match", true)
                        .queryParam("tournoi", true)
                        .queryParam("federaux", true)
                        .queryParam("reunion", true)
                        .queryParam("autre", false)
                        .queryParam("datesFutures", false)
                        .back()
                    .method(Request.GET)
                    .fetch()
                    .binary();
            LOG.debug("Response for team {} is '{}'", team, new String(response));

            JAXBContext context = JAXBContext.newInstance(EventContainer.class);
            Unmarshaller um = context.createUnmarshaller();

            EventContainer ec = (EventContainer) um.unmarshal(new ByteArrayInputStream(response));
            return ec.getEvents();
        } catch (IOException | JAXBException ex) {
            throw new V34Exception(ex);
        }
    }
}
