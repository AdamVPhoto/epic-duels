package games.epicduels.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateGameMessage implements Serializable {

    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
