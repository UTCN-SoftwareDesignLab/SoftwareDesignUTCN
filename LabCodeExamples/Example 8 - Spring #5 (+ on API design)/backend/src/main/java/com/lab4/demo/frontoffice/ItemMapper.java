package com.lab4.demo.frontoffice;

import com.lab4.demo.frontoffice.model.Item;
import com.lab4.demo.frontoffice.model.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    Item fromDto(ItemDTO item);

}
