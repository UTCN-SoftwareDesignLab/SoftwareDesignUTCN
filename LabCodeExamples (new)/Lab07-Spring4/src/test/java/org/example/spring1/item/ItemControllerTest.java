package org.example.spring1.item;

import org.example.spring1.core.SpringControllerBaseTest;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.example.spring1.UrlMapping.ID_PART;
import static org.example.spring1.UrlMapping.ITEMS;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ItemControllerTest extends SpringControllerBaseTest {

  @InjectMocks
  private ItemController itemController;

  @Mock
  private ItemService itemService;

  @BeforeEach
  public void setUp() {
    super.setUp();
    itemController = new ItemController(itemService);
    mvc = buildForController(itemController);
  }

  @Test
  void findAll() throws Exception {
    List<ItemDTO> items = new ArrayList<>();
    int nrItems = 10;
    for (int i = 0; i < nrItems; i++) {
      items.add(ItemDTO.builder().name(String.valueOf(i)).build());
    }
    when(itemService.findAll()).thenReturn(items);

    ResultActions result = performGet(ITEMS);

    result.andExpect(status().isOk()).andExpect(contentToBe(items));
  }

  @Test
  void findAllFiltered() {
  }

  @Test
  void get() throws Exception {
    long id = 1L;
    ItemDTO dto = ItemDTO.builder().id(id).name("name").description("descrip").dateCreated(LocalDate.now()).build();
    ResponseEntity<?> res = ResponseEntity.ok(dto);

    doReturn(res).when(itemService).get(id);

    ResultActions resultActions = performGetWithPathVariables(ITEMS + ID_PART, id);

    verify(itemService, times(1)).get(id);

    resultActions.andExpect(status().isOk()).andExpect(contentToBe(dto));
  }

  @Test
  void getNotFound() throws Exception {
    long id = 1L;
    ResponseEntity<?> res = ResponseEntity.notFound().build();

    doReturn(res).when(itemService).get(id);

    ResultActions resultActions = performGetWithPathVariables(ITEMS + ID_PART, id);

    verify(itemService, times(1)).get(id);

    resultActions.andExpect(status().isNotFound());
  }

  @Test
  void create() throws Exception {
    ItemRequestDTO requestDto = ItemRequestDTO.builder().name("name").description("description").build();
    ItemDTO responseDto = ItemDTO.builder().id(2L).name("name").description("descrip").dateCreated(LocalDate.now()).build();

    when(itemService.create(requestDto)).thenReturn(responseDto);

    ResultActions resultActions = performPostWithRequestBody(ITEMS, requestDto);

    verify(itemService, times(1)).create(requestDto);

    resultActions.andExpect(status().isOk()).andExpect(contentToBe(responseDto));
  }

  @Test
  void delete() {
  }

  @Test
  void deleteMultiple() {
  }

  @Test
  void update() {
  }

  @Test
  void changeName() {
  }
}
