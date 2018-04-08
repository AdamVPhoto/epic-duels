package games.epicduels.server.listener;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static EventManager instance = new EventManager();

    private List<EventListener> eventListeners;

    public static EventManager getInstance() {
        return instance;
    }

    private EventManager() {
        this.eventListeners = new ArrayList<>();
    }

    public void addEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }
    
    public void addEvent(String event) {
        eventListeners.stream().forEach((eventListener) -> {
            eventListener.addEvent(event);
        });
    }
}
