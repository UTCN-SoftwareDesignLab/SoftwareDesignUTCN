package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemServiceTest {

  @Autowired
  private ItemService itemService;
  @Autowired
  private ItemRepository itemRepository;

  @Test
  void findAll() {
    assertEquals(0, itemService.findAll().size());

    Item item1 = Item.builder().name("bla bla").build();
    itemRepository.save(item1);

    assertEquals(1, itemService.findAll().size());
  }

  @Test
  void empty() {

  }
}