package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemDTO;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    private Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
    }

    public List<ItemDTO> findAll() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    public ItemDTO create(ItemDTO item) {
        return itemMapper.toDto(itemRepository.save(
                itemMapper.fromDto(item)
        ));
    }

    public ItemDTO edit(Long id, ItemDTO item) {
        Item actItem = findById(id);
        actItem.setName(item.getName());
        actItem.setDescription(item.getDescription());
        return itemMapper.toDto(
                itemRepository.save(actItem)
        );
    }

    public ItemDTO changeName(Long id, String newName) {
        Item item = findById(id);
        item.setName(newName);
        return itemMapper.toDto(
                itemRepository.save(item)
        );
    }

    public ItemDTO get(Long id) {
        return itemMapper.toDto(findById(id));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Page<ItemDTO> findAllFiltered(ItemFilterRequestDto filter, Pageable pageable) {
        return itemRepository.findAll(
                ItemSpecifications.specificationsFromFilter(filter), pageable
        ).map(itemMapper::toDto);
    }
}
