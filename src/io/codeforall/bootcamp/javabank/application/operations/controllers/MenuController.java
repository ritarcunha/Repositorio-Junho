package io.codeforall.bootcamp.javabank.application.operations.controllers;

import io.codeforall.bootcamp.javabank.application.BankApplication;
import io.codeforall.bootcamp.javabank.application.Controller;
import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.application.operations.views.MenuView;

import java.util.HashMap;
import java.util.Map;

public class MenuController implements Controller {

    MenuView menuView = new MenuView();
    BankApplication bankApplication;
    Map<Integer, Controller> controllerMap;


    public MenuController(BankApplication bankApplication) {
        this.bankApplication = bankApplication;
        this.controllerMap = buildControllersMap();
    }

    @Override
    public void execute() {
        int choice = menuView.show();

        if (choice == UserOptions.QUIT.getOption()) {
            return;
        }

        controllerMap.get(choice).execute();
        execute();
    }

    @Override
    public void nextController() {

    }

    private Map<Integer, Controller> buildControllersMap() {

        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceController(bankApplication));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositController(bankApplication));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawController(bankApplication));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountController(bankApplication));

        return map;
    }

}
