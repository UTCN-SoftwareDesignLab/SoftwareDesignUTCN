package com.lab4.demo.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private String text;
    private String reviewer;
}
