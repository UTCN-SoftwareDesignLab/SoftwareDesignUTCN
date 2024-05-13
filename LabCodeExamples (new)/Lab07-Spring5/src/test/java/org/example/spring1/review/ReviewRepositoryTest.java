package org.example.spring1.review;

import org.example.spring1.core.SpringIntegrationBaseTest;
import org.example.spring1.item.persistence.ItemRepository;
import org.example.spring1.item.model.Item;
import org.example.spring1.review.model.Review;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReviewRepositoryTest extends SpringIntegrationBaseTest {

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    reviewRepository.deleteAll();
    itemRepository.deleteAll();
    userRepository.deleteAll();
  }

  @Test
  void findAllByUser_Id() {
    Item item1 = itemRepository.save(Item.builder().name("item 1").build());
    Item item2 = itemRepository.save(Item.builder().name("item 2").build());
    Item item3 = itemRepository.save(Item.builder().name("item 3").build());

    User user1 = userRepository.save(User.builder().username("user 1").email("emial@gmailc.om").password("blabla").build());
    User user2 = userRepository.save(User.builder().username("user 2").email("emial2@gmailc.om").password("blabla").build());
    User user3 = userRepository.save(User.builder().username("user 3").email("emial3@gmailc.om").password("blabla").build());

    Review review11 = reviewRepository.save(Review.builder().text("text11").item(item1).user(user1).rating(5).build());
    Review review12 = reviewRepository.save(Review.builder().text("text12").item(item2).user(user1).rating(5).build());
    Review review13 = reviewRepository.save(Review.builder().text("text13").item(item3).user(user1).rating(5).build());

    Review review21 = reviewRepository.save(Review.builder().text("text21").item(item1).user(user2).rating(5).build());
    Review review22 = reviewRepository.save(Review.builder().text("text22").item(item2).user(user2).rating(5).build());

    Review review31 = reviewRepository.save(Review.builder().text("text31").item(item1).user(user3).rating(5).build());

    List<Review> user1Reviews = reviewRepository.findAllByUser_Id(user1.getId());

    assertEquals(3, user1Reviews.size());
    List.of(review11.getId(), review12.getId(), review13.getId())
        .forEach(id -> assertTrue(user1Reviews.stream().anyMatch(review -> review.getId().equals(id))));
  }
}