package com.lab4.demo.review;

import com.lab4.demo.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Set<Review> findAllByItem_Id(Long itemId);
}
