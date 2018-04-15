package games.epicduels.server.message;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance = new DataManager();

    public static DataManager getInstance() {
        return instance;
    }
    
    private List<String> games;
    
    private DataManager() {
        games = new ArrayList<>();
    }
    
    public void addGame(String game) {
        games.add(game);
    }

    public List<String> getGames() {
        return games;
    }
}
