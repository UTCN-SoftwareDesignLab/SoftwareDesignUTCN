package org.example.spring1.item;

import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemServiceUnitTest {

  private ItemService itemService;

  @Mock
  private ItemRepository itemRepository;
  @Mock
  private ItemMapper itemMapper;

  @BeforeAll
  void setUp() {
    MockitoAnnotations.openMocks(this);
    itemService = new ItemService(itemRepository, itemMapper);
  }

  @Test
  void findAll() {
    Item item1 = Item.builder().id(-1L).build();
    Item item2 = Item.builder().id(0L).name("item2").build();
    Item item3 = Item.builder().id(1L).name("item3").build();

    List<Item> preparedItems = List.of(item1, item2, item3);

    when(itemRepository.findAll()).thenReturn(preparedItems);

    List<ItemDTO> returnedItems = itemService.findAll();
    assertEquals(preparedItems.size(), returnedItems.size());
  }
}
