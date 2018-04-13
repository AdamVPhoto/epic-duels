package games.epicduels.client.controllers;

import games.epicduels.client.message.MessageListener;

public abstract class CommonController implements MessageListener {

    protected MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public abstract void init();
}
