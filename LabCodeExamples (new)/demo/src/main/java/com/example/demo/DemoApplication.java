package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);

    Item item1 = Item.builder()
        .id(-1L)
        .name("name")
        .description("description")
        .build();


  }

}
