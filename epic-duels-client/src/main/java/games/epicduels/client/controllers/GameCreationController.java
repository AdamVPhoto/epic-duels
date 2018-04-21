package games.epicduels.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.message.GameCreationStatus;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameCreationController extends CommonController {
    
    private static Logger LOG = (Logger) LogManager.getLogger(GameCreationController.class);
    
    @FXML private VBox teamsBox;
    @FXML private HBox selectedTeamsBox;
    private HBox hBox;

    @Override
    public void init() {
        
    }
    
    @Override
    public void updateMessage(Object message) {
        
        if (message instanceof GameCreationStatus) {
            GameCreationStatus gameCreationStatus = (GameCreationStatus) message;
            hBox = new HBox();
            
            gameCreationStatus.getTeams().forEach(team -> {
                
                if (hBox.getChildren().size() == 9) {
                    teamsBox.getChildren().add(hBox);
                    hBox = new HBox();
                }
                
                Button button = new Button();
                try {
                    button.setGraphic(new ImageView(new Image(new FileInputStream(team.getImage()))));
                } catch (FileNotFoundException e) {
                    LOG.error(e.getMessage(), e);
                }
                
                hBox.getChildren().add(button);
            });
            
            teamsBox.getChildren().add(hBox);
            
            gameCreationStatus.getTeamSlots().forEach(teamSlot -> {
                Button button = new Button(teamSlot.getUser());
                selectedTeamsBox.getChildren().add(button);
            });
        }
    }
}
