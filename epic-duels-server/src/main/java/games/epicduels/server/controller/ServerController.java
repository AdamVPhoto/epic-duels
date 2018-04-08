package games.epicduels.server.controller;

import games.epicduels.server.listener.EventListener;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ServerController implements EventListener {
    
    @FXML TextArea statusText;

    @Override
    public void addEvent(String event) {
        Platform.runLater(() -> {
            statusText.appendText(event + "\n");
        });
    }

}
