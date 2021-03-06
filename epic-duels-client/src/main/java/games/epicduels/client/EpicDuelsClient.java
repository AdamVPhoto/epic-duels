package games.epicduels.client;

import java.io.FileInputStream;

import games.epicduels.client.controllers.MainController;
import games.epicduels.client.controllers.UserSelectionController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EpicDuelsClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxml = new FXMLLoader();
        MainController mainController = new MainController();
        fxml.setController(mainController);
        Parent root = fxml.load(new FileInputStream(getClass().getResource("/fxml/MainView.fxml").getFile()));
        mainController.init(stage);
        mainController.loadController(new UserSelectionController(), "/fxml/UserSelectionView.fxml");

        Scene scene = new Scene(root, 500, 500);

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
