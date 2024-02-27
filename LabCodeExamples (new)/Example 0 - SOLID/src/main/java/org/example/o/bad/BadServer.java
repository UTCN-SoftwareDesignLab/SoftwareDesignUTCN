package org.example.o.bad;

public class BadServer {

  public void reactToClient(BadWebClient client) {
    client.doSomething();
  }

  public void reactToClient(BadMobileClient client) {
    client.doSomething();
  }

  public void reactToClient(BadTabletClient client) {
    client.doSomething();
  }
}
