package org.jared.v34.ics.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Equipe")
public class TeamContainer {

    List<Team> teams;

    public List<Team> getTeams() {
        return teams;
    }

    @XmlElement(name = "Row")
    public TeamContainer setTeams(List<Team> teams) {
        this.teams = teams;
        return this;
    }

    public TeamContainer addTeam(Team team) {
        if (teams == null) {
            teams = new ArrayList<>();
        }
        teams.add(team);
        return this;
    }
}
