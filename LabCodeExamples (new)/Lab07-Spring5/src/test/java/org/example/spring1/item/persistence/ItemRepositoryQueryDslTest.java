package org.example.spring1.item.persistence;

import org.example.spring1.core.SpringIntegrationBaseTest;
import org.example.spring1.item.model.Item;
import org.example.spring1.review.ReviewRepository;
import org.example.spring1.review.model.Review;
import org.example.spring1.user.UserRepository;
import org.example.spring1.user.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemRepositoryQueryDslTest extends SpringIntegrationBaseTest {

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
  void allItemsWithAtLeastOneReviewRated() {
    Item item1 = itemRepository.save(Item.builder().name("item 1").build());
    Item item2 = itemRepository.save(Item.builder().name("item 2").build());
    Item item3 = itemRepository.save(Item.builder().name("item 3").build());

    User user1 = userRepository.save(User.builder().username("user 1").email("emial@gmailc.om").password("blabla").build());
    User user2 = userRepository.save(User.builder().username("user 2").email("emial2@gmailc.om").password("blabla").build());
    User user3 = userRepository.save(User.builder().username("user 3").email("emial3@gmailc.om").password("blabla").build());

    Review review11 = reviewRepository.save(Review.builder().text("text11").item(item1).user(user1).rating(5).build());
    Review review12 = reviewRepository.save(Review.builder().text("text12").item(item2).user(user1).rating(4).build());
    Review review13 = reviewRepository.save(Review.builder().text("text13").item(item3).user(user1).rating(3).build());

    Review review21 = reviewRepository.save(Review.builder().text("text21").item(item1).user(user2).rating(6).build());
    Review review22 = reviewRepository.save(Review.builder().text("text22").item(item2).user(user2).rating(5).build());

    Review review31 = reviewRepository.save(Review.builder().text("text31").item(item1).user(user3).rating(7).build());

    List<Item> itemsWithRatingBiggerThan6 = itemRepository.allItemsWithAtLeastOneReviewRated(6);
    assertEquals(1, itemsWithRatingBiggerThan6.size());
    assertEquals(item1.getId(), itemsWithRatingBiggerThan6.get(0).getId());

    List<Item> itemsWithRatingBiggerThan5 = itemRepository.allItemsWithAtLeastOneReviewRated(5);
    assertEquals(2, itemsWithRatingBiggerThan5.size());

    List<Item> itemsWithRatingBiggerThan4 = itemRepository.allItemsWithAtLeastOneReviewRated(4);
    assertEquals(2, itemsWithRatingBiggerThan4.size());

    List<Item> itemsWithRatingBiggerThan3 = itemRepository.allItemsWithAtLeastOneReviewRated(3);
    assertEquals(3, itemsWithRatingBiggerThan3.size());
  }
}