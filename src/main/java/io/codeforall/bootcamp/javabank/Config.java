package io.codeforall.bootcamp.javabank;

import io.codeforall.bootcamp.javabank.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Application configuration settings
 */
public class Config {

    public static final String H2_PORT = "8082";
    public static final String PERSISTENCE_UNIT = "dev";

}
