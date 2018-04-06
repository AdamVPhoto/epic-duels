package games.epicduels.client;

import java.io.FileInputStream;

import games.epicduels.client.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EpicDuelsClient extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader fxml = new FXMLLoader();
		MainController mainController = new MainController();
		fxml.setController(mainController);
		Parent root = fxml.load(new FileInputStream(getClass().getResource("/fxml/MainView.fxml").getFile()));
		mainController.init();
		
		Scene scene = new Scene(root, 500, 500);
		
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
