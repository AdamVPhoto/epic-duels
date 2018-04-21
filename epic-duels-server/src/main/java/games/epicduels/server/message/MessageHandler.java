package games.epicduels.server.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.message.CreateGameMessage;
import games.epicduels.message.GameCreationStatus;
import games.epicduels.message.GamesStatus;
import games.epicduels.message.JoinGameMessage;
import games.epicduels.message.SendTextMessage;
import games.epicduels.message.TextMessageResponse;
import games.epicduels.server.connection.ClientConnection;
import games.epicduels.server.connection.ServerConnection;
import games.epicduels.server.listener.EventManager;

public class MessageHandler {
    
    private static Logger LOG = (Logger) LogManager.getLogger(MessageHandler.class);
    
    private ServerConnection serverConnection;
    private String username;
    private DataManager dataManager;
    private ClientConnection clientConnection;
    
    public MessageHandler(ServerConnection serverConnection, String username, ClientConnection clientConnection) {
        this.serverConnection = serverConnection;
        this.clientConnection = clientConnection;
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
            
            EventManager.getInstance().addEvent(createGameMessage.getGameName() + " game created.");
            LOG.info(createGameMessage.getGameName() + " game created.");
            
            GameCreationStatus gameCreationStatus = new GameCreationStatus();
            gameCreationStatus.setTeams(createGameMessage.getTeams());
            
            dataManager.addGame(createGameMessage.getGameName(), gameCreationStatus);
            dataManager.addTeamSlot(createGameMessage.getGameName(), username);
            
            GamesStatus gamesStatus = new GamesStatus();
            gamesStatus.setGameNames(dataManager.getGames());
            
            serverConnection.sendMessageToAllClients(gamesStatus);
            clientConnection.sendMessage(gameCreationStatus);
        } else if (message instanceof JoinGameMessage) {
            JoinGameMessage joinGameMessage = (JoinGameMessage) message;
            clientConnection.sendMessage(dataManager.getCreatedGame(joinGameMessage.getGameName()));
        }
    }
}
