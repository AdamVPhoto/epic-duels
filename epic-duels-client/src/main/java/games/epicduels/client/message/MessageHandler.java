package games.epicduels.client.message;

import java.util.ArrayList;
import java.util.List;

import games.epicduels.client.connection.Connection;
import javafx.application.Platform;

public class MessageHandler {
    
    private static MessageHandler instance = new MessageHandler();
    
    public static MessageHandler getInstance() {
        return instance;
    }
    
    private List<MessageListener> messageListeners;
    private Connection connection;
    private String userName;

    private MessageHandler() {
        messageListeners = new ArrayList<>();
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
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
    
    public void sendMessage(Object message) {
        connection.sendMessage(message);
    }
}
