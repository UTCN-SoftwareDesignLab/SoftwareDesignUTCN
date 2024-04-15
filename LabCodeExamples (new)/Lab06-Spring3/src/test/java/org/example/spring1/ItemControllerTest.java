package org.example.spring1;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;

import org.example.spring1.item.ItemController;
import org.example.spring1.item.ItemService;
import org.example.spring1.item.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTest {

  private MockMvc mockMvc;
  private ItemController itemController;

  @Mock
  ItemService itemService;

  @BeforeAll
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    itemController = new ItemController(itemService);
    mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
  }

  @Test
  void findAll() throws Exception {
    List<Item> items = new ArrayList<>();
    int nrItems = 10;
    for (int i = 0; i < nrItems; i++) {
      items.add(Item.builder().name(String.valueOf(i)).build());
    }

    when(itemService.findAll()).thenReturn(items);

    ResultActions result = mockMvc.perform(get(UrlMapping.ITEMS));

    String serializedItems = new ObjectMapper().writeValueAsString(items);

    result.andExpect(status().isOk()).andExpect(content().json(serializedItems));
  }
}
