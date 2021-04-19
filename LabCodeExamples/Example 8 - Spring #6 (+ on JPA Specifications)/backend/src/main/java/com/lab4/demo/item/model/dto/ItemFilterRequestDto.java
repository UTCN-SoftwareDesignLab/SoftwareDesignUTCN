package com.lab4.demo.item.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemFilterRequestDto {
    @Builder.Default
    private final String name = "";
    @Builder.Default
    private final LocalDateTime createdAfter = null;
    @Builder.Default
    private final Boolean onlyExcellent = false;
    @Builder.Default
    private final Boolean withAdminReview = false;
}
