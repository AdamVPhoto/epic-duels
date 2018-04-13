package games.epicduels.message;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class UsersStatus implements Serializable {

    private List<String> users;

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
