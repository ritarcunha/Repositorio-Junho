package io.codeforall.bootcamp.javabank.controllers;

import io.codeforall.bootcamp.javabank.application.UserOptions;
import io.codeforall.bootcamp.javabank.controllers.transaction.DepositController;
import io.codeforall.bootcamp.javabank.controllers.transaction.WithdrawController;
import io.codeforall.bootcamp.javabank.domain.Bank;
import io.codeforall.bootcamp.javabank.viewers.MenuView;

import java.util.HashMap;
import java.util.Map;

public class MenuController extends AbstractBankController{

    private MenuView menuView = new MenuView(this);
    private Map<Integer, Controller> controllersMap;

    public MenuController(Bank bank,int customerId){
        super(bank, customerId);
        controllersMap = buildControllersMap();
    }

    public void nextController(int choice){
        if (choice == UserOptions.QUIT.getOption()) {
            return;
        }

        controllersMap.get(choice).execute();
        execute();
    }

    private Map<Integer, Controller> buildControllersMap() {

        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceController(bank, customerId));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositController(bank, customerId));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawController(bank, customerId));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountController(bank, customerId));

        return map;
    }

    @Override
    public void execute() {
        menuView.show();
    }
}
