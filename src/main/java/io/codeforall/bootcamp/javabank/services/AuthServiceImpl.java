package io.codeforall.bootcamp.javabank.services;

import io.codeforall.bootcamp.javabank.domain.Customer;

import java.util.HashMap;
import java.util.Set;

public class AuthServiceImpl implements AuthService{

    private HashMap<Integer,Customer> customers;
    private int loginCustomer;

    public AuthServiceImpl(){
        this.customers=customers;
        this.loginCustomer=loginCustomer;
    }

    @Override
    public boolean authenticate(Integer id) {//tenho de ir buscar a lista dos login e ver se está la //se tiver retorna true

        if(getCustomerIds().equals(id)){
            return true;
        }

        return false;
    }

    public Set<Integer> getCustomerIds() {// vai buscar os ids
        return customers.keySet();
    }

    public void setLoginCustomer(int id) {//o login do user vai ser igual ao seu id // vai receber um id (dado pelo utilizador) e passar lhe ao logincustomer
        this.loginCustomer = id;
    }

    @Override
    public Customer getAccessingCustomer() {
        return customers.get(loginCustomer);
    }

    //duvidas aqui faz sentido manter o setLoginCustomer, porque na verdade eu tenho de atualizar o login customer
}
