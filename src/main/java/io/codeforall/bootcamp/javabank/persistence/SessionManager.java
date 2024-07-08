package io.codeforall.bootcamp.javabank.persistence;

import java.sql.Connection;

public interface SessionManager {

    void startSession();
    void stopSession();
    Connection getCurrentSession();
}
