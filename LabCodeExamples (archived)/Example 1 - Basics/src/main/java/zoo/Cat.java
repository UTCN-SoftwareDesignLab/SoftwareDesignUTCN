package zoo;

public class Cat extends Animal {
  @Override
  public void talk() {
    System.out.printf("Meow! I am a %s\n", getName());
  }

  @Override
  public String getName() {
    return "CAT";
  }

  public void purr() {
    System.out.printf("Purr! I am a %s\n", getName());
  }
}
