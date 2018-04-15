package games.epicduels.server.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.message.CreateGameMessage;
import games.epicduels.message.GamesStatus;
import games.epicduels.message.SendTextMessage;
import games.epicduels.message.TextMessageResponse;
import games.epicduels.server.connection.ServerConnection;
import games.epicduels.server.listener.EventManager;

public class MessageHandler {
    
    private static Logger LOG = (Logger) LogManager.getLogger(MessageHandler.class);
    
    private ServerConnection serverConnection;
    private String username;
    private DataManager dataManager;
    
    public MessageHandler(ServerConnection serverConnection, String username) {
        this.serverConnection = serverConnection;
        this.username = username;
        dataManager = DataManager.getInstance();
    }

    public void handleMessage(Object message) {
        
        if (message instanceof SendTextMessage) {
            SendTextMessage sendTextMessage = (SendTextMessage) message;
            EventManager.getInstance().addEvent(username + ": " + sendTextMessage.getTextMessage());
            LOG.info(username + ": " + sendTextMessage.getTextMessage());
            TextMessageResponse textMessageResponse = new TextMessageResponse();
            textMessageResponse.setTextMessage(sendTextMessage.getTextMessage());
            textMessageResponse.setUser(username);
            serverConnection.sendMessageToAllClients(textMessageResponse);
        } else if (message instanceof CreateGameMessage) {
            CreateGameMessage createGameMessage = (CreateGameMessage) message;
            dataManager.addGame(createGameMessage.getGameName());
            EventManager.getInstance().addEvent(createGameMessage.getGameName() + " game created.");
            LOG.info(createGameMessage.getGameName() + " game created.");
            GamesStatus gamesStatus = new GamesStatus();
            gamesStatus.setGameNames(dataManager.getGames());
            serverConnection.sendMessageToAllClients(gamesStatus);
        }
    }
}
