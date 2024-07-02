package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.model.account.SavingsAccount;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private AuthService authService;
    private View view;
    private Controller nextController;
    private LoginController loginController;


    @Before
    public void setUp() {
        authService = mock(AuthService.class);
        view = mock(View.class);
        nextController = mock(Controller.class);
        loginController = mock(LoginController.class);
        doCallRealMethod().when(loginController).setAuthService(authService);
        doCallRealMethod().when(loginController).setNextController(nextController);
        doCallRealMethod().when(loginController).setView(view);
        loginController.setAuthService(authService);
        loginController.setNextController(nextController);
        loginController.setView(view);


    }

    @Test
    public void onLoginAuthenticateTrueTest() {

        when(authService.authenticate(2)).thenReturn(true);
        doCallRealMethod().when(loginController).onLogin(2);

        loginController.onLogin(2);

        verify(nextController).init();
    }

    @Test
    public void onLoginAuthenticateFalseTest() {

        when(authService.authenticate(2)).thenReturn(false);
        doCallRealMethod().when(loginController).onLogin(2);

        loginController.onLogin(2);

        verify(loginController).init();
    }
}



    /*@Override
    public boolean canDebit(double amount) {
        return super.canDebit(amount) && (getBalance() - amount) >= MIN_BALANCE;
    }*/

    /*@Override
    public boolean canDebit(double amount) {
        return amount > 0 && amount <= balance;
    }*/

    /*@Override
    public double getBalance() {
        return balance;
    }*/

