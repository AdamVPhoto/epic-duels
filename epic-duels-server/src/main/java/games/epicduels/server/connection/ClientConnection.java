package games.epicduels.server.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ClientConnection {
    
    private static Logger LOG = (Logger) LogManager.getLogger(ClientConnection.class);
    
    private ObjectOutputStream output;

    public ClientConnection(ObjectOutputStream output) {
        this.output = output;
    }

    public void listenForMessages(ObjectInputStream input) throws ClassNotFoundException, IOException {

        while (true) {
            input.readObject();
        }
    }
    
    public void sendMessage(Object message) {
        
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
            if (output != null) {
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
