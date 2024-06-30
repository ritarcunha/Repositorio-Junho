package io.codeforall.bootcamp.javabank.viewers;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.controllers.MenuController;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MenuView implements View{

    private MenuController menuController;
    private MenuInputScanner mainMenu;

    public MenuView(MenuController menuController){
        this.menuController = menuController;
        mainMenu = new MenuInputScanner(UserOptions.getMessages());
    }

    @Override
    public void show() {
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        menuController.nextController(prompt.getUserInput(mainMenu));
    }
}
