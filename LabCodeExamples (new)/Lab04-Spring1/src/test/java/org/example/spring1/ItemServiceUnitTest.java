package org.example.spring1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import org.example.spring1.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemServiceUnitTest {

  private ItemService itemService;

  @Mock private ItemRepository itemRepository;

  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    itemService = new ItemService(itemRepository);
  }

  @Test
  void findAll() {
    Item item1 = Item.builder().id(-1L).build();
    Item item2 = Item.builder().id(0L).name("item2").build();
    Item item3 = Item.builder().id(1L).name("item3").build();

    List<Item> preparedItems = List.of(item1, item2, item3);

    when(itemRepository.findAll()).thenReturn(preparedItems);

    List<Item> returnedItems = itemService.findAll();
    assertEquals(preparedItems.size(), returnedItems.size());
    assertEquals(preparedItems, returnedItems);
  }
}
