package games.epicduels.message.data;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Team implements Serializable {

    private String name;
    private Character majorCharacter;
    private List<Character> minorCharacters;
    private String image;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getMajorCharacter() {
        return majorCharacter;
    }
    
    public void setMajorCharacter(Character majorCharacter) {
        this.majorCharacter = majorCharacter;
    }
    
    public List<Character> getMinorCharacters() {
        return minorCharacters;
    }
    
    public void setMinorCharacters(List<Character> minorCharacters) {
        this.minorCharacters = minorCharacters;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
