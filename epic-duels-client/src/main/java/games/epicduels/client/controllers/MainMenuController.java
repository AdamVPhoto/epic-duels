package games.epicduels.client.controllers;

import games.epicduels.message.UsersStatus;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainMenuController extends CommonController {
    
    @FXML ListView<String> playerListView;

    @Override
    public void init() {
        
    }

    @Override
    public void updateMessage(Object message) {
        
        if (message instanceof UsersStatus) {
            playerListView.getItems().clear();
            playerListView.getItems().addAll(((UsersStatus) message).getUsers());
        }
    }
}
