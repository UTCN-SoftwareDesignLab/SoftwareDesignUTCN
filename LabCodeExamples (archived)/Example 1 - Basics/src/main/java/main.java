import zoo.Animal;
import zoo.Cat;
import zoo.Dog;
import zoo.Hooman;
import zoo.Talkable;

import java.util.List;

public class main {
  public static void main(String[] args) {
    Talkable dog1 = new Dog();
    Animal dog2 = new Dog();
    Dog dog3 = new Dog();

    Talkable cat1 = new Cat();
    Animal cat2 = new Cat();
    Cat cat3 = new Cat();

    Talkable hooman1 = new Hooman();

    List<Talkable> talkables = List.of(dog1, dog2, dog3, cat1, cat2, cat3, hooman1);

    List<Animal> animals = List.of(dog2, dog3, cat2, cat3);

    List<Cat> cats = List.of(cat3);
//
//    talkables.forEach(Talkable::talk);
//    final List<String> collect = animals.stream()
//        .map(Animal::getName)
//        .distinct()
//        .collect(Collectors.toList());
//
//    System.out.println(collect);

  }
}
