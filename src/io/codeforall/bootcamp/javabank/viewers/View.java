package io.codeforall.bootcamp.javabank.viewers;

import org.academiadecodigo.bootcamp.Prompt;

public interface View {

    Prompt prompt = new Prompt(System.in, System.out);

    void show();
}
