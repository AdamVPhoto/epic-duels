package games.epicduels.server.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import games.epicduels.message.GameCreationStatus;
import games.epicduels.message.data.TeamSlot;

public class DataManager {

    private static DataManager instance = new DataManager();

    public static DataManager getInstance() {
        return instance;
    }
    
    private Map<String, GameCreationStatus> games;
    
    private DataManager() {
        games = new LinkedHashMap<>();
    }
    
    public void addGame(String game, GameCreationStatus gameCreation) {
        games.put(game, gameCreation);
    }

    public List<String> getGames() {
        return Arrays.asList(games.keySet().toArray(new String[games.size()]));
    }
    
    public GameCreationStatus getCreatedGame(String gameName) {
        return games.get(gameName);
    }
    
    public void addTeamSlot(String game, String user) {
        
        List<TeamSlot> teamSlots = games.get(game).getTeamSlots();
        
        if (teamSlots == null) {
            teamSlots = new ArrayList<>();
            games.get(game).setTeamSlots(teamSlots);
        }
        
        TeamSlot teamSlot = new TeamSlot();
        teamSlot.setUser(user);
        teamSlots.add(teamSlot);
    }
}
