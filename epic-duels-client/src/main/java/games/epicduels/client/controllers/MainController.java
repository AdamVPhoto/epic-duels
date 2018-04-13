package games.epicduels.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.client.message.MessageHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController {
    
    private static Logger LOG = (Logger) LogManager.getLogger(MainController.class);
    
    private Stage stage;

    @FXML MenuBar menuBar;
    @FXML AnchorPane mainPane;

    public void init(Stage stage) throws FileNotFoundException, IOException {
        this.stage = stage;
        menuBar.setUseSystemMenuBar(true);
    }
    
    public void loadController(CommonController controller, String fxmlFile) {
        
        MessageHandler.getInstance().addMessageListener(controller);
        FXMLLoader fxml = new FXMLLoader();
        fxml.setController(controller);
        
        try {
            Node node = fxml.load(new FileInputStream(getClass().getResource(fxmlFile).getFile()));
            controller.init();
            controller.setMainController(this);

            AnchorPane.setLeftAnchor(node, 0.0);
            AnchorPane.setRightAnchor(node, 0.0);
            AnchorPane.setTopAnchor(node, 0.0);
            AnchorPane.setBottomAnchor(node, 0.0);
            
            mainPane.getChildren().clear();
            mainPane.getChildren().add(node);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    public void setMaximized(boolean maximized) {
        stage.setMaximized(maximized);
    }
    
    public void setFullScreen(boolean fullScreen) {
        stage.setFullScreen(fullScreen);
    }
}
