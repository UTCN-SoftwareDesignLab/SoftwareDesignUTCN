package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    void findAll() {
        List<Item> items = new ArrayList<>();
        int nrItems = 10;
        for (int i = 0; i < nrItems; i++) {
            items.add(Item.builder().name(String.valueOf(i)).build());
        }

        when(itemRepository.findAll()).thenReturn(items);

        List<Item> all = itemService.findAll();

        Assertions.assertEquals(nrItems, all.size());
    }
}
