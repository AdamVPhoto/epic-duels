package games.epicduels.server.connection;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.server.listener.EventManager;

public class ServerConnection {
    
    private static Logger LOG = (Logger) LogManager.getLogger(ServerConnection.class);

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
            try (ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
                // Wait for user name request
                Object message = input.readObject();
                
                if (message instanceof String) {
                    String username = (String) message;
                    
                    LOG.info(username + " has joined.");
                    EventManager.getInstance().addEvent(username + " has joined.");
                    
                    ClientConnection clientConnection = new ClientConnection();
                    clientConnection.listenForMessages(input);
                } else {
                    LOG.error("No user name was received");
                }
            } catch (EOFException ex) {
                
            } catch (IOException | ClassNotFoundException e) {
                LOG.error(e.getMessage(), e);
            }
        }).start();
    }
}
