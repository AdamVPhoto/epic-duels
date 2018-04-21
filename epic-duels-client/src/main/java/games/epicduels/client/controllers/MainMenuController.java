package games.epicduels.client.controllers;

import java.util.Optional;

import games.epicduels.client.message.MessageHandler;
import games.epicduels.client.utils.GameDataUtil;
import games.epicduels.message.CreateGameMessage;
import games.epicduels.message.GamesStatus;
import games.epicduels.message.JoinGameMessage;
import games.epicduels.message.SendTextMessage;
import games.epicduels.message.TextMessageResponse;
import games.epicduels.message.UsersStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class MainMenuController extends CommonController {
    
    @FXML private ListView<String> playerListView;
    @FXML private TextField textMessageField;
    @FXML private TextArea textMessagesArea;
    @FXML private ListView<String> gamesListView;
    @FXML private Button joinGameButton;
    
    private MessageHandler messageHandler;
    
    public MainMenuController() {
        messageHandler = MessageHandler.getInstance();
    }

    @Override
    public void init() {
        
        gamesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    joinGameButton.setDisable(false);
                }
            }
        });
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
        } else if (message instanceof GamesStatus) {
            gamesListView.getItems().clear();
            gamesListView.getItems().addAll(((GamesStatus) message).getGameNames());
        }
    }
    
    @FXML
    public void sendTextMessage() {
        SendTextMessage sendTextMessage = new SendTextMessage();
        sendTextMessage.setTextMessage(textMessageField.getText());
        messageHandler.sendMessage(sendTextMessage);
        textMessageField.setText("");
    }
    
    @FXML
    public void createGame() {
        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Enter Game Name:");
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Create Game");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(gameName ->  {
            mainController.loadController(new GameCreationController(), "/fxml/GameCreationView.fxml");
            CreateGameMessage createGameMessage = new CreateGameMessage();
            createGameMessage.setGameName(gameName);
            createGameMessage.setTeams(GameDataUtil.loadTeams());
            messageHandler.sendMessage(createGameMessage);
        });
    }
    
    @FXML
    public void joinGame() {
        
        mainController.loadController(new GameCreationController(), "/fxml/GameCreationView.fxml");
        JoinGameMessage joinGameMessage = new JoinGameMessage();
        joinGameMessage.setGameName(gamesListView.getSelectionModel().getSelectedItem());
        messageHandler.sendMessage(joinGameMessage);
    }
}
