package io.codeforall.bootcamp.javabank.application.view;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.View;
import io.codeforall.bootcamp.javabank.application.operations.Controllers.MenuController;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MenuView implements View {
    private MenuController menuController;
    private Prompt prompt = new Prompt(System.in,System.out);

    private Integer selectedOption;


    public MenuView (MenuController menuController){
        this.menuController=menuController;
    }



    @Override
    public void show() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);
        selectedOption = prompt.getUserInput(mainMenu);

    }

    public int getSelectedOption(){
        return this.selectedOption;
    }



    //o utilizador escolhe uma opçao do menu
}
