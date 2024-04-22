package org.example.spring1.item;

import lombok.RequiredArgsConstructor;
import org.example.spring1.global.SingleBodyRequestDTO;
import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.dto.ItemDTO;
import org.example.spring1.item.model.dto.ItemRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.example.spring1.UrlMapping.*;

@RestController
@RequestMapping(ITEMS)
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping
  public List<Item> findAll() {
    return itemService.findAll();
  }

  @GetMapping(FILTERED_ITEMS_PART)
  public List<Item> findAllFiltered(@RequestParam String name) {
    return itemService.findAllFiltered(name);
  }

  @GetMapping(ID_PART)
  public ResponseEntity<?> get(@PathVariable Long id) {
    return itemService.get(id);
  }

  @PostMapping
  public ItemDTO create(@RequestBody ItemRequestDTO dto) {
    return itemService.create(dto);
  }

  @DeleteMapping(ID_PART)
  public void delete(@PathVariable Long id) {
    itemService.delete(id);
  }

  @DeleteMapping
  public void deleteMultiple(@RequestParam List<Long> ids) {
    itemService.deleteMultiple(ids);
  }

  @PutMapping(ID_PART)
  public ItemDTO update(@PathVariable Long id, @RequestBody ItemRequestDTO dto) {
    return itemService.update(id, dto);
  }

  @PatchMapping(ID_PART + CHANGE_NAME_PART)
  public ItemDTO changeName(@PathVariable Long id, @RequestBody SingleBodyRequestDTO<String> dto) {
    return itemService.changeName(id, dto.getBody());
  }
}
