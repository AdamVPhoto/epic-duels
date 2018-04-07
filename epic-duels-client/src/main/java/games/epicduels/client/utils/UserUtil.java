package games.epicduels.client.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class UserUtil {
    
    private static Logger LOG = (Logger) LogManager.getLogger(UserUtil.class);
    
    public static List<String> getUsers() {

        List<String> users = new ArrayList<>();
        File userFile = new File("config/users.txt");

        if (!userFile.exists()) {
            try {
                if (!new File("config").mkdirs()) {
                    return users;
                }
                if (!userFile.createNewFile()) {
                    return users;
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        try (InputStream in = new FileInputStream(userFile);
                Reader read = new InputStreamReader(in, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(read)) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (!users.contains(line)) {
                    users.add(line);
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        return users;
    }

    public static void addUser(String user) {

        List<String> users = getUsers();

        try (OutputStream out = new FileOutputStream("config/users.txt");
                Writer write = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(write)) {
            
            users.forEach((currentUser) -> {
                writer.println(currentUser);
            });
            
            if (!users.contains(user)) {
                writer.println(user);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    public static void removeUser(String user) {

        List<String> users = getUsers();

        try (OutputStream out = new FileOutputStream("config/users.txt");
                Writer write = new OutputStreamWriter(out, StandardCharsets.UTF_8);
                PrintWriter writer = new PrintWriter(write)) {
            
            users.forEach((currentUser) -> {
                if (!user.equals(currentUser)) {
                    writer.println(currentUser);
                }
            });
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
