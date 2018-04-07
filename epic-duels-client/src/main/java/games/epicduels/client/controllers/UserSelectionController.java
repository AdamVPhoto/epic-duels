package games.epicduels.client.controllers;

import java.util.List;
import java.util.Optional;

import games.epicduels.client.utils.UserUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class UserSelectionController extends CommonController {
    
    @FXML ListView<String> userListView;
    @FXML Button removeUserButton;
    @FXML Button selectUserButton;

    @Override
    public void init() {
        userListView.getItems().addAll(UserUtil.getUsers());
        userListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    removeUserButton.setDisable(false);
                    selectUserButton.setDisable(false);
                }
            }
        });
    }

    @FXML
    public void addNewUser() {

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Enter username:");
        ((Button) dialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Add User");
        
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(user ->  {
            UserUtil.addUser(user);
            userListView.getItems().clear();
            userListView.getItems().addAll(UserUtil.getUsers());
            removeUserButton.setDisable(true);
            selectUserButton.setDisable(true);
        });
    }
    
    @FXML
    public void removeUser() {
        
        String user = userListView.getSelectionModel().getSelectedItem();
        
        if (user == null) {
            return;
        }
        
        ButtonType yesButton = new ButtonType("Yes", ButtonData.OK_DONE);
        ButtonType noButton = new ButtonType("No", ButtonData.CANCEL_CLOSE);
        
        Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to remove user " + user);
        alert.getButtonTypes().setAll(yesButton, noButton);
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton){
            UserUtil.removeUser(user);
            List<String> users = UserUtil.getUsers();
            
            userListView.getItems().clear();
            userListView.getItems().addAll(users);
            removeUserButton.setDisable(true);
            selectUserButton.setDisable(true);
        }
    }
    
    @FXML
    public void selectUser() {
        mainController.loadController(new MainMenuController(), "/fxml/MainMenuView.fxml");
    }
}
