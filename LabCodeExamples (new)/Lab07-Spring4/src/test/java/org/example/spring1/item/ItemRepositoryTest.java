package org.example.spring1.item;

import static org.junit.jupiter.api.Assertions.*;

import org.example.spring1.item.ItemRepository;
import org.example.spring1.item.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class ItemRepositoryTest {

  @Autowired private ItemRepository itemRepository;

  @BeforeEach
  void cleanup() {
    itemRepository.deleteAll();
  }

  @Test
  void basic() {
    assertEquals(0, itemRepository.count());

    Item item1 = Item.builder().name("item name 1").build();

    Item savedItem1 = itemRepository.save(item1);

    assertTrue(savedItem1.getId() > 0);
  }

  @Test
  void constraintViolationTests() {
    Item justDescription = Item.builder().description("just description").build();

    assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(justDescription));

    Item longAssName =
        Item.builder()
            .name(
                "longassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassnamelongassname")
            .build();

    assertThrows(DataIntegrityViolationException.class, () -> itemRepository.save(longAssName));
  }
}
