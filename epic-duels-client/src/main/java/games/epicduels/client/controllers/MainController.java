package games.epicduels.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class MainController {
    
    private static Logger LOG = (Logger) LogManager.getLogger(MainController.class);

    @FXML MenuBar menuBar;
    @FXML AnchorPane mainPane;

    public void init() throws FileNotFoundException, IOException {

        menuBar.setUseSystemMenuBar(true);
    }
    
    public void loadController(CommonController controller, String fxmlFile) {
        
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
}
