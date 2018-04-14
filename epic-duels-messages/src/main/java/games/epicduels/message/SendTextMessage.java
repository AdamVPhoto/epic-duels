package games.epicduels.message;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SendTextMessage implements Serializable {

    private String textMessage;

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
