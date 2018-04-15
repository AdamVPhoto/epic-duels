package games.epicduels.message;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class GamesStatus implements Serializable {

    private List<String> gameNames;

    public List<String> getGameNames() {
        return gameNames;
    }

    public void setGameNames(List<String> gameNames) {
        this.gameNames = gameNames;
    }
}
