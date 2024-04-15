package org.example.spring1;

import org.example.spring1.item.model.Item;
import org.example.spring1.user.model.ERole;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
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
