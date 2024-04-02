package org.example.spring1;

import static org.example.spring1.UrlMapping.ITEMS;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.spring1.model.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
