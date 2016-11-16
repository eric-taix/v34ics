package org.jared.v34.ics.services;

import com.jcabi.http.Request;
import com.jcabi.http.request.ApacheRequest;
import org.jared.v34.ics.model.Team;
import org.jared.v34.ics.model.TeamContainer;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@Service
public class V34Service {

    private static final String V34_URL = "http://webservices.volley34.fr";
    private static final String GET_EQUIPES = "/wsEquipes.asmx/GetEquipesCompetition";

    public List<Team> getTeams() throws JAXBException, IOException {
        byte[] response = new ApacheRequest(V34_URL + GET_EQUIPES)
                .method(Request.GET)
                .fetch()
                .binary();

        JAXBContext context = JAXBContext.newInstance(TeamContainer.class);

        Unmarshaller um = context.createUnmarshaller();
        TeamContainer tc = (TeamContainer) um.unmarshal(new ByteArrayInputStream(response));
        return tc.getTeams();
    }

}
