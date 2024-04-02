package org.example.spring1;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.spring1.model.Item;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<Item> findAll() {
    return itemRepository.findAll();
  }
}
