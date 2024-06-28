package io.codeforall.bootcamp.javabank.application;

import io.codeforall.bootcamp.javabank.application.operations.BalanceOperation;
import io.codeforall.bootcamp.javabank.application.operations.NewAccountOperation;
import io.codeforall.bootcamp.javabank.application.operations.Operation;
import io.codeforall.bootcamp.javabank.application.operations.controllers.*;
import io.codeforall.bootcamp.javabank.application.operations.transaction.DepositOperation;
import io.codeforall.bootcamp.javabank.application.operations.transaction.WithdrawOperation;
import io.codeforall.bootcamp.javabank.application.operations.views.LoginView;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import io.codeforall.bootcamp.javabank.domain.Bank;

import java.util.HashMap;
import java.util.Map;

/**
 * The bank application
 */
public class BankApplication {

    private Prompt prompt;
    private MenuInputScanner mainMenu;
    private Map<Integer, Controller> controllersMap;

    private Bank bank;


    private int accessingCustomerId;

    /**
     * Creates a new instance of a {@code BankApplication}, initializes it with the given {@link Bank}
     *
     * @param bank the bank instance
     */
    public BankApplication(Bank bank) {
        this.bank = bank;
        this.prompt = new Prompt(System.in, System.out);
    }

    public void setAccessingCustomerId(int accessingCustomerId) {
        this.accessingCustomerId = accessingCustomerId;
    }
    /**
     * Gets the prompt used for the UI
     *
     * @return the prompt
     */
    public Prompt getPrompt() {
        return prompt;
    }

    /**
     * Gets the bank used by this application
     *
     * @return the bank
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Gets the id of the customer using the Bank Application
     *
     * @return the customer id
     */
    public int getAccessingCustomerId() {
        return accessingCustomerId;
    }

    /**
     * Starts the bank application
     */
    public void start() {

        mainMenu = buildMainMenu();
        LoginController loginController=new LoginController(this);
        loginController.execute();
        loginController.nextController();
        //accessingCustomerId = scanCustomerId();
        controllersMap = buildControllersMap();
       //menuLoop();
    }

    private void menuLoop() {

       /* int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop();*/
    }

    private int scanCustomerId() {

        IntegerSetInputScanner scanner = new IntegerSetInputScanner(bank.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);
    }

    private MenuInputScanner buildMainMenu() {

        MenuInputScanner mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

        return mainMenu;
    }

    private Map<Integer, Controller> buildControllersMap() {

        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(),new BalanceController(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositController(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawController(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountController(this));

        return map;
    }
}
