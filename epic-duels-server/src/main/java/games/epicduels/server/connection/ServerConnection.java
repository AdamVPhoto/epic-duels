package games.epicduels.server.connection;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.message.UsersStatus;
import games.epicduels.server.listener.EventManager;
import games.epicduels.server.message.MessageHandler;

public class ServerConnection {
    
    private static Logger LOG = (Logger) LogManager.getLogger(ServerConnection.class);
    
    private Map<String, ClientConnection> clientConnections = new TreeMap<>();

    public void startServer(int port) {
        
        new Thread(() ->  {
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                LOG.info("Started server at port " + port);
                EventManager.getInstance().addEvent("Started server at port " + port);
                
                // Listen for new connections
                while (true) {
                    handleClientConnected(serverSocket.accept());
                }
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        }).start();
    }
    
    private void handleClientConnected(Socket socket) {
        
        LOG.info("New client has connected");
        
        new Thread(() ->  {
            
            String username = "";
            
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                // Wait for user name request
                Object message = input.readObject();
                
                if (message instanceof String) {
                    username = (String) message;
                    
                    LOG.info(username + " has joined.");
                    EventManager.getInstance().addEvent(username + " has joined.");
                    
                    ClientConnection clientConnection = new ClientConnection(new ObjectOutputStream(socket.getOutputStream()));
                    clientConnections.put(username, clientConnection);
                    
                    // Send user names
                    UsersStatus status = new UsersStatus();
                    status.setUsers(Arrays.asList(clientConnections.keySet().toArray(new String[clientConnections.size()])));
                    sendMessageToAllClients(status);
                    
                    clientConnection.listenForMessages(input, new MessageHandler(this, username, clientConnection));
                } else {
                    LOG.error("No user name was received");
                }
            } catch (EOFException | SocketException ex) {
                LOG.info(username + " has left.");
                clientConnections.get(username).close();
                clientConnections.remove(username);
                EventManager.getInstance().addEvent(username + " has left.");
                
                try {
                    socket.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            } catch (IOException | ClassNotFoundException e) {
                LOG.error(e.getMessage(), e);
            }
        }).start();
    }
    
    public void sendMessageToAllClients(Object message) {
        
        clientConnections.forEach((key, clientConnection) -> {
            clientConnection.sendMessage(message);
        });
    }
}
