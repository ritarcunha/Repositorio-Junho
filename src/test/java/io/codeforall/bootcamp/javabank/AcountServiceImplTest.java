package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.Controller;
import io.codeforall.bootcamp.javabank.model.account.Account;
import io.codeforall.bootcamp.javabank.services.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class AcountServiceImplTest {

    private Map<Integer, Account> accountMap;
    private AccountServiceImpl accountServiceImpl;

    private Account account;



    @Before
    public void StepUp(){

        accountServiceImpl= new AccountServiceImpl();
        account=mock(Account.class);
        //accountMap=mock(HashMap.class);
        //accountServiceImpl.set// tenho de fazer uma set
        accountServiceImpl.add(account);

        //accountServiceImpl=mock(AccountServiceImpl.class);
//o valor default de um add é 0
    }

    @Test
    public void depositTest (){

       // when (accountMap.get(1)).thenReturn((account));
        accountServiceImpl.deposit(0,10);
        verify(account).credit(10);



    }

    @Test
    public  void withdrawTest (){
        when (account.canWithdraw()).thenReturn(false);
        accountServiceImpl.withdraw(0,10);
        verify(account, never()).debit(10);
    }


    /* public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);


    }*/


}
