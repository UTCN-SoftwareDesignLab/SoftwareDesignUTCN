package org.example.spring1.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
  private Long id;
  private String name;
  private String description;
  private LocalDate dateCreated;
}
