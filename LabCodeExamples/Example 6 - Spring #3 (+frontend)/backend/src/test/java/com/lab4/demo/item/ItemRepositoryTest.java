package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void testMock() {
        Item itemSaved = repository.save(Item.builder().name("whatever").build());

        assertNotNull(itemSaved);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(Item.builder().build());
        });
    }

    @Test
    public void testFindAll() {
        int nrItems = 10;
        for (int i = 0; i < nrItems; i++) {
            repository.save(Item.builder().name(String.valueOf(i)).build());
        }
        List<Item> all = repository.findAll();
        assertEquals(nrItems, all.size());
    }
}
