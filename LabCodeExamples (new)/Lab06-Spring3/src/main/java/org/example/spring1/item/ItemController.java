package org.example.spring1.item;

import static org.example.spring1.UrlMapping.ITEMS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.spring1.item.model.Item;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public List<Item> findAll() {
    return itemService.findAll();
  }
}
