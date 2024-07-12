package io.codeforall.bootcamp.javabank.persistence;

import javax.persistence.EntityManagerFactory;

public interface SessionManager {
    /**
     * Starts the session
     */
    EntityManagerFactory startSession();

    /**
     * Stops the session
     */
    void stopSession();

    //T getCurrentSession();
}
