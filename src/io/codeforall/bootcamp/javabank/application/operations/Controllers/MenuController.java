package io.codeforall.bootcamp.javabank.application.operations.Controllers;

import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.operations.Controller;
import io.codeforall.bootcamp.javabank.application.view.MenuView;
import io.codeforall.bootcamp.javabank.domain.Bank;

import java.util.HashMap;
import java.util.Map;

public class MenuController implements Controller {

    private Bank bank;
    private MenuView menuView;

    Map<Integer, Controller> map;

    public MenuController(Bank bank) {
        this.bank = bank;
        this.menuView = new MenuView(this);
        this.map = new HashMap<>();
    }

    @Override
    public void execute() {

        menuView.show();

               ; //metodo que constroi a hashmap com a lista dos comandos e dos controllers

          if (menuView.getSelectedOption() == UserOptions.QUIT.getOption()) {
                      return;
                  }

          buildControllerMap().get(menuView.getSelectedOption()).execute();    //vai buscar um dos controllers representado pelo numero da opçao

    }

    @Override
    public void nextController() {


    }

    private Map<Integer, Controller> buildControllerMap() {


        map.put(UserOptions.GET_BALANCE.getOption(), new MenuController(bank));
        map.put(UserOptions.DEPOSIT.getOption(), new MenuController(bank));
        map.put(UserOptions.WITHDRAW.getOption(), new MenuController(bank));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new MenuController(bank));

        return map;
    }
}




