package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.demo.UrlMapping.ITEMS;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public List<Item> test() {
    return itemService.findAll();
  }

  @PostMapping
  public void test2() {

  }

  @PostMapping(value = UrlMapping.SECOND_METHOD)
  public void test3() {

  }

}
