package games.epicduels.client.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;

public class MainController {
	
	@FXML MenuBar menuBar;
	@FXML AnchorPane mainPane;

	public void init() throws FileNotFoundException, IOException {
		
		menuBar.setUseSystemMenuBar(true);
		
		FXMLLoader fxml = new FXMLLoader();
		Node node = fxml.load(new FileInputStream(getClass().getResource("/fxml/UserSelectionView.fxml").getFile()));
	
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setBottomAnchor(node, 0.0);
		mainPane.getChildren().add(node);
	}
}
