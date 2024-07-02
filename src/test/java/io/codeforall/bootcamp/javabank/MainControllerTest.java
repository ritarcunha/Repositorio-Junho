package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.controller.LoginController;
import io.codeforall.bootcamp.javabank.controller.MainController;
import io.codeforall.bootcamp.javabank.services.AuthService;
import io.codeforall.bootcamp.javabank.view.UserOptions;
import io.codeforall.bootcamp.javabank.view.View;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.*;

public class MainControllerTest {

    private AuthService authService;
    private View view;
    private Map<Integer, Controller> controllerMap;

    private MainController mainController;


    @Before
    public void setUp() {
        authService = mock(AuthService.class);
        view = mock(View.class);
        mainController = mock(MainController.class);
        controllerMap = mock(Map.class);

        doCallRealMethod().when(mainController).setAuthService(authService);
        doCallRealMethod().when(mainController).setView(view);
        doCallRealMethod().when(mainController).setControllerMap(controllerMap);
        doCallRealMethod().when(mainController).onMenuSelection(anyInt());

        mainController.setAuthService(authService);
        mainController.setView(view);
        mainController.setControllerMap(controllerMap);
    }

    @Test
    public void onMenuSelectionIfQuitTest(){
        //when(UserOptions.QUIT.getOption()).thenReturn(5);//na funciona com isto

        mainController.onMenuSelection(5);

        verify(mainController, never()).init();//O never serve para ele verificar se o metodo init nao for invocado;
    }

    @Test(expected = IllegalStateException.class)
    public void onMenuSelectionDoesntContainKeyTest(){

        when(controllerMap.containsKey(6)).thenReturn(false);

        mainController.onMenuSelection(6);

        //verify(controllerMap, never()).get(6);// Se pussessemos o init ele dava nullpointer porque o controller map esta vazio por ser um mock

    }

    @Test
    public void onMenuSelectionTest(){
        //when(UserOptions.QUIT.getOption()).thenReturn(1);//na funciona com isto
        when(controllerMap.containsKey(1)).thenReturn(true);
        when(controllerMap.get(1)).thenReturn(mock(Controller.class));//como esta com um mock associado ele nao vai fazer o init

        mainController.onMenuSelection(1);

        verify(mainController).init();

    }

}
