package games.epicduels.message;

import java.io.Serializable;
import java.util.List;

import games.epicduels.message.data.Team;

@SuppressWarnings("serial")
public class CreateGameMessage implements Serializable {

    private String gameName;
    private List<Team> teams;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
