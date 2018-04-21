package games.epicduels.message;

import java.io.Serializable;
import java.util.List;

import games.epicduels.message.data.Team;
import games.epicduels.message.data.TeamSlot;

@SuppressWarnings("serial")
public class GameCreationStatus implements Serializable {

    private List<Team> teams;
    private List<TeamSlot> teamSlots;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<TeamSlot> getTeamSlots() {
        return teamSlots;
    }

    public void setTeamSlots(List<TeamSlot> teamSlots) {
        this.teamSlots = teamSlots;
    }
}
