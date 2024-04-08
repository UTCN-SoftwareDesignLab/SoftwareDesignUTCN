package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemServiceUnitTest {

  private ItemService itemService;

  @Mock
  private ItemRepository itemRepository;

  @BeforeAll
  void setup() {
    MockitoAnnotations.openMocks(this);
    this.itemService = new ItemService(itemRepository);
  }

  @Test
  void findAll() {
    Item item1 = Item.builder().name("item1").build();
    Item item2 = Item.builder().name("item2").build();
    Item item3 = Item.builder().name("item3").build();
    List<Item> preparedItems = List.of(item1, item2, item3);

    when(itemRepository.findAll()).thenReturn(preparedItems);

    List<Item> returnedItems = itemService.findAll();
    assertEquals(3, returnedItems.size());
    assertEquals(preparedItems, returnedItems);
  }
}