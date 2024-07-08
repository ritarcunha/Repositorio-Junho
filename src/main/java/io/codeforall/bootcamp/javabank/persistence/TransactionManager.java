package io.codeforall.bootcamp.javabank.persistence;

public interface TransactionManager {

    void beginRead();
    void beginWrite();
    void commit();
    void rollback();
}
