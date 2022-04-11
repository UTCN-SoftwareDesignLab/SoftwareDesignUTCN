package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    Item fromDto(ItemDTO item);

}
