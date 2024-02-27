package org.example.o.good;

public class GoodWebClient implements Client {

    @Override
    public void doSomething() {
        System.out.println("I am a web client doing something.");
    }
}
