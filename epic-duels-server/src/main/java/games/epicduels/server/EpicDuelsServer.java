package games.epicduels.server;

import java.io.FileInputStream;

import games.epicduels.server.connection.ServerConnection;
import games.epicduels.server.controller.ServerController;
import games.epicduels.server.listener.EventManager;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class EpicDuelsServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxml = new FXMLLoader();
        ServerController serverController = new ServerController();
        fxml.setController(serverController);
        Parent root = fxml.load(new FileInputStream(getClass().getResource("/fxml/ServerView.fxml").getFile()));

        Scene scene = new Scene(root, 500, 500);

        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        
        EventManager.getInstance().addEventListener(serverController);
        ServerConnection serverConnection = new ServerConnection();
        serverConnection.startServer(5000);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
