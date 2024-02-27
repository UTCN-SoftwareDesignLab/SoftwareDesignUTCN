package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.lab4.demo.UrlMapping.ITEMS;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> allItems() {
        return itemService.findAll();
    }
}
