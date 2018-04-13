package games.epicduels.client.message;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;

public class MessageHandler {
    
    private static MessageHandler instance = new MessageHandler();
    
    public static MessageHandler getInstance() {
        return instance;
    }
    
    private List<MessageListener> messageListeners;

    private MessageHandler() {
        messageListeners = new ArrayList<>();
    }
    
    public void handleMessage(Object message) {
        Platform.runLater(() -> {
            messageListeners.forEach((messageListener) -> {
                messageListener.updateMessage(message);
            });
        });
    }
    
    public void addMessageListener(MessageListener messageListener) {
        messageListeners.add(messageListener);
    }
}
