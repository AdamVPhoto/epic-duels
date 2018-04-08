package games.epicduels.client.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class Connection {
    
    private static Logger LOG = (Logger) LogManager.getLogger(Connection.class);
    
    private Socket socket;
    private ObjectOutputStream output;

    public void connect(String hostname, int port, String username) throws UnknownHostException, IOException {
        
        socket = new Socket(hostname, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        LOG.info("Connected to server at " + hostname + ":" + port);
        
        // Send username to server
        sendMessage(username);
        
        new Thread(() -> {
            
        }).start();
    }
    
    private void sendMessage(Object message) {
        
        try {
            output.reset();
            output.writeObject(message);
            output.flush();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    public void close() {
        
        try {
            if (socket != null) {
                socket.close();
            }
            
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
