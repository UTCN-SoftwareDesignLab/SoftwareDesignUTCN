package com.lab4.demo.review;

import com.lab4.demo.item.ItemRepository;
import com.lab4.demo.item.model.Item;
import com.lab4.demo.review.model.Review;
import com.lab4.demo.review.model.dto.ReviewDTO;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static com.lab4.demo.TestCreationFactory.randomEmail;
import static com.lab4.demo.TestCreationFactory.randomString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @AfterEach
    void cleanup() {
        reviewRepository.deleteAll();
        itemRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void getReviewsForItem() {
        User user = User.builder().username(randomString()).password(randomString()).email(randomEmail()).build();
        userRepository.save(user);
        Item item = Item.builder().name(randomString()).description(randomString()).build();
        item = itemRepository.save(item);

        Review reviewToBeAdded = Review.builder().item(item).user(user).text(randomString()).build();
        reviewRepository.save(reviewToBeAdded);

        Set<ReviewDTO> reviewsForItem = reviewService.getReviewsForItem(item.getId());

        assertEquals(1, reviewsForItem.size());
    }
}