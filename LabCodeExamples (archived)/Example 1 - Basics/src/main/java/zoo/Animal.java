package zoo;

public abstract class Animal implements Talkable {

  public abstract String getName();

  public int getRandomNumber() {
    return (int) (Math.random() * 10);
  }

}
