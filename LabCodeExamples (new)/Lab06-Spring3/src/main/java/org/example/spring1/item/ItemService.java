package org.example.spring1.item;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.spring1.item.model.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<Item> findAll() {
    return itemRepository.findAll();
  }
}
