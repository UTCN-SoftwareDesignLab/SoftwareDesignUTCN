package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @BeforeEach
  void cleanup() {
    itemRepository.deleteAll();
  }

  @Test
  void basic() {
    assertEquals(0, itemRepository.count());

    Item item1 = Item.builder().name("name").description("description").build();

    Item savedItem1 = itemRepository.save(item1);

    assertTrue(savedItem1.getId() > 0);
  }

  @Test
  void constraintValidation() {
    Item item1 = Item.builder().name("name123name123name123name123name123name123name123name123name123name123name123name123name123").description("description").build();

    assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(item1));

    Item item2 = Item.builder().description("description").build();

    assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(item2));
  }
}