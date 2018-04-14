package games.epicduels.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;

//import org.apache.commons.cli.BasicParser;
//import org.apache.commons.cli.CommandLine;
//import org.apache.commons.cli.CommandLineParser;
//import org.apache.commons.cli.DefaultParser;
//import org.apache.commons.cli.Options;
//import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.client.connection.Connection;
import games.epicduels.client.controllers.MainController;
import games.epicduels.client.controllers.MainMenuController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddUsersTest extends Application {
    
    private static Logger LOG = (Logger) LogManager.getLogger(AddUsersTest.class);
    
    private static String user;

    public static void main(String[] args) throws IOException {
        
//        -Djvm=/Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home/bin/java
//        -Dstart.external.programs=true
        
//        System.out.println(System.getProperties());
//        System.out.println(System.getProperty("java.class.path"));
//        for (String classpath: System.getProperty("java.class.path").split(":")) {
//            System.out.println(classpath);
//        }
        
//        String jvm = null;
//        
//        Options options = new Options();
//        
//        options.addOption("jvm", true, "jvm Path");
//        
//        CommandLineParser parser = new DefaultParser();
//        CommandLine cmd = parser.parse(options, args);
//
//        if (cmd.hasOption("jvm")) {
//            jvm = cmd.getOptionValue("jvm");
//        } else {
//            throw new ParseException("Cannot find argument jvm");
//        }

        if (System.getProperty("start.external.programs") != null && 
                System.getProperty("start.external.programs").equals("true")) {
            
            startJavaProgram("games.epicduels.server.EpicDuelsServer");
            startJavaProgram("games.epicduels.test.AddUsersTest");
            user = "Killer Whale";
        } else {
            user = "Panda";
        }
        
        Application.launch(args);
    }

    private static void startJavaProgram(String classname) throws IOException {
        
        new Thread(() -> {
            
            ProcessBuilder builder = new ProcessBuilder(
                    System.getProperty("jvm"),
                    "-Dlog4j.configurationFile=log4j.xml", 
                    "-Dfile.encoding=UTF-8", 
                    "-classpath",
                    System.getProperty("java.class.path"),
                    classname);

            try {
                Process process = builder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }).start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        Connection connection = new Connection();
        boolean success = false;
        
        while (!success) {
            try {
                connection.connect("localhost", 5000, user);
                success = true;
            } catch (ConnectException e) {
                LOG.error("Unable to connect, trying again");
            }
        }
        
        FXMLLoader fxml = new FXMLLoader();
        MainController mainController = new MainController();
        fxml.setController(mainController);
        Parent root = fxml.load(new FileInputStream(getClass().getResource("/fxml/MainView.fxml").getFile()));
        mainController.init(stage);
        mainController.loadController(new MainMenuController(), "/fxml/MainMenuView.fxml");

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
}
