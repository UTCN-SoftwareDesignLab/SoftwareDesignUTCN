package org.example.spring1.review;

import org.example.spring1.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


  List<Review> findAllByUser_Id(Long userId);

}
