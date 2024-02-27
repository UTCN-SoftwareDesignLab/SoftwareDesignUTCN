package zoo;

public class Dog extends Animal {
  @Override
  public void talk() {
    System.out.printf("Woof! I am a %s\n", getName());
  }

  @Override
  public String getName() {
    return "DOG";
  }

  public void dogSpecificMethod() {
    System.out.println("This is a dog specific method");
  }

}
