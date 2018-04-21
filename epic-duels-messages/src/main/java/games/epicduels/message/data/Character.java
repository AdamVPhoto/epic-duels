package games.epicduels.message.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Character implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
