package io.codeforall.bootcamp.javabank.persistence;

public interface SessionManager {
    /**
     * Starts the session
     */
    void startSession();

    /**
     * Stops the session
     */
    void stopSession();

    //T getCurrentSession();
}
