package games.epicduels.client.controllers;

import games.epicduels.client.message.MessageHandler;
import games.epicduels.message.SendTextMessage;
import games.epicduels.message.TextMessageResponse;
import games.epicduels.message.UsersStatus;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainMenuController extends CommonController {
    
    @FXML ListView<String> playerListView;
    @FXML TextField textMessageField;
    @FXML TextArea textMessagesArea;

    @Override
    public void init() {
        
    }

    @Override
    public void updateMessage(Object message) {
        
        if (message instanceof UsersStatus) {
            playerListView.getItems().clear();
            playerListView.getItems().addAll(((UsersStatus) message).getUsers());
        } else if (message instanceof TextMessageResponse) {
            TextMessageResponse textMessageResponse = (TextMessageResponse) message;
            textMessagesArea.appendText(textMessageResponse.getUser() + ": " + 
                    textMessageResponse.getTextMessage() + "\n");
        }
    }
    
    @FXML
    public void sendTextMessage() {
        SendTextMessage sendTextMessage = new SendTextMessage();
        sendTextMessage.setTextMessage(textMessageField.getText());
        MessageHandler.getInstance().sendMessage(sendTextMessage);
        textMessageField.setText("");
    }
}
