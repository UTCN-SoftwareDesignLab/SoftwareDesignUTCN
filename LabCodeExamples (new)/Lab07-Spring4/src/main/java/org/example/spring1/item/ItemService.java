package org.example.spring1.item;

import lombok.RequiredArgsConstructor;
import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;

  public List<Item> findAll() {
    return itemRepository.findAll();
  }

  public ResponseEntity<?> get(Long id) {
    return itemRepository.findById(id)
        .map(itemMapper::fromEntity)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  public ItemDTO create(ItemRequestDTO dto) {
    return null;
  }

  public void delete(Long id) {

  }

  public List<Item> findAllFiltered(String name) {
    return null;
  }

  public ItemDTO update(Long id, ItemRequestDTO dto) {
    return null;
  }

  public ItemDTO changeName(Long id, String newName) {
    return null;
  }

  public void deleteMultiple(List<Long> ids) {

  }
}
