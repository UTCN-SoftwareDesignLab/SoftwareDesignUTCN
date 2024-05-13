package org.example.spring1.item.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.spring1.review.model.Review;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 512)
  private String description;

  @Column(nullable = false)
  @Builder.Default
  private LocalDate dateCreated = LocalDate.now();

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  private List<Review> reviews;
}
