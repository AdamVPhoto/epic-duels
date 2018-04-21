package games.epicduels.message.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TeamSlot implements Serializable {

    private String user;
    private Team team;
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
    
    public Team getTeam() {
        return team;
    }
    
    public void setTeam(Team team) {
        this.team = team;
    }
}
