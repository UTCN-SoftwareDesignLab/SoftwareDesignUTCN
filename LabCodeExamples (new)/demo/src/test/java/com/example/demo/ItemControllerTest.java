package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.UrlMapping.ITEMS;
import static com.example.demo.UrlMapping.SECOND_METHOD;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemControllerTest {

  private MockMvc mockMvc;
  private ItemController itemController;

  @Mock
  ItemService itemService;

  @BeforeAll
  public void setup() {
    MockitoAnnotations.openMocks(this);
    itemController = new ItemController(itemService);
    mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
  }

  @Test
  void test() throws Exception {
    List<Item> items = new ArrayList<>();
    int nrItems = 10;
    for (int i = 0; i < nrItems; i++) {
      items.add(Item.builder().name(String.valueOf(i)).build());
    }
    when(itemService.findAll()).thenReturn(items);

    ResultActions resultActions = mockMvc.perform(post(ITEMS));

    String serializedItems = new ObjectMapper().writeValueAsString(items);

    resultActions.andExpect(status().isOk());

    ResultActions resultActions2 = mockMvc.perform(post(ITEMS + SECOND_METHOD));
    resultActions2.andExpect(status().isOk());
  }

}
