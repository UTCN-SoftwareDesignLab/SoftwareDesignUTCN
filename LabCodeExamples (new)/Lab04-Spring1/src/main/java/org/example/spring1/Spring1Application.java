package org.example.spring1;

import org.example.spring1.model.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Spring1Application {

  public static void main(String[] args) {
    SpringApplication.run(Spring1Application.class, args);

    Item build = Item.builder().id(-1L).name("name").description("descriere").build();

    String description = build.getDescription();
    build.setId(-2L);

    Item item1 = new Item(-1L, "name", "descript");
    Item item2 = new Item();
  }

}
