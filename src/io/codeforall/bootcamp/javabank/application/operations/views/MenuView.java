package io.codeforall.bootcamp.javabank.application.operations.views;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.View;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MenuView implements View {

    Prompt prompt= new Prompt(System.in, System.out);


    @Override
    public Integer show() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        int userChoice = prompt.getUserInput(mainMenu);
        return userChoice;


    }
}
