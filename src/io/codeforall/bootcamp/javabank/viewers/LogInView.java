package io.codeforall.bootcamp.javabank.viewers;

import io.codeforall.bootcamp.javabank.application.Messages;
import io.codeforall.bootcamp.javabank.controllers.LogInController;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;

import javax.jws.Oneway;

public class LogInView implements View{

    private LogInController logInController;

    public LogInView(LogInController logInController){
        this.logInController = logInController;
    }

    @Override
    public void show() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(logInController.getCustomerIds());
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        logInController.nextController(prompt.getUserInput(scanner));
    }
}
