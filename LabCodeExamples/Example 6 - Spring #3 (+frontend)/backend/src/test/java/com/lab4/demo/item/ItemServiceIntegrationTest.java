package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ItemServiceIntegrationTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository.deleteAll();
    }

    @Test
    void findAll() {
        int nrItems = 10;
        for (int i = 0; i < nrItems; i++) {
            itemRepository.save(Item.builder().name(String.valueOf(i)).build());
        }

        List<Item> all = itemService.findAll();

        Assertions.assertEquals(nrItems, all.size());
    }
}
