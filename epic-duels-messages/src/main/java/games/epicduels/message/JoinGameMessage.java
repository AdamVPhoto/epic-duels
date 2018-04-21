package games.epicduels.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class JoinGameMessage implements Serializable {

    private String gameName;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
