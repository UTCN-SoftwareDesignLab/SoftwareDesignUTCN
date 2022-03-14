package com.lab4.demo.frontoffice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab4.demo.frontoffice.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.lab4.demo.UrlMapping.ITEMS;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest {

    protected MockMvc mockMvc;

    @InjectMocks
    private ItemController controller;

    @Mock
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ItemController(itemService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allItems() throws Exception {
        List<Item> items = new ArrayList<>();
        int nrItems = 10;
        for (int i = 0; i < nrItems; i++) {
            items.add(Item.builder().name(String.valueOf(i)).build());
        }

        when(itemService.findAll()).thenReturn(items);

        ResultActions response = mockMvc.perform(get(ITEMS));

        String expectedJsonContent = new ObjectMapper().writeValueAsString(items);
        response.andExpect(status().isOk())
                .andExpect(content().json(expectedJsonContent, true));

    }
}
