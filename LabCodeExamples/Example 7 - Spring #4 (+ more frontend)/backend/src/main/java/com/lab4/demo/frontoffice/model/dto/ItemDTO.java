package com.lab4.demo.frontoffice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
}
