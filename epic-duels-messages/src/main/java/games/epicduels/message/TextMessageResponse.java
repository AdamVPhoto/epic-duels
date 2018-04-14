package games.epicduels.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TextMessageResponse implements Serializable {

    private String textMessage;
    private String user;
    
    public String getTextMessage() {
        return textMessage;
    }
    
    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
    
    public String getUser() {
        return user;
    }
    
    public void setUser(String user) {
        this.user = user;
    }
}
