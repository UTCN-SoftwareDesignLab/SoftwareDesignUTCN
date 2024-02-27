package com.lab4.demo.item.model;

import com.lab4.demo.review.model.Review;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;

    @Column
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "item")
    @Builder.Default
    private Set<Review> reviews = new HashSet<>();
}
