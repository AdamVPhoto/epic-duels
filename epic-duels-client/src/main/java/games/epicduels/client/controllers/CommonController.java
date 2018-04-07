package games.epicduels.client.controllers;

public abstract class CommonController {

    protected MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public abstract void init();
}
