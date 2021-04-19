package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import com.lab4.demo.review.model.Review;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.time.LocalDateTime;

import static com.lab4.demo.review.Rating.EXCELENT;

public class ItemSpecifications {

  public static Specification<Item> similarNames(String name) {
    return (Specification<Item>) (root, query, cb) -> cb.like(root.get("name"), name);
  }

  public static Specification<Item> createdAfter(LocalDateTime date) {
    return (Specification<Item>) (root, query, cb) -> cb.greaterThan(root.get("dateCreated"), date);
  }

  public static Specification<Item> onlyExcellentRated() {
    return (Specification<Item>) (root, query, criteriaBuilder) -> criteriaBuilder.exists(reviewRatingSubquery(root,
        query, criteriaBuilder));
  }

  public static Specification<Item> withAdministratorReviews() {
    return (Specification<Item>) (root, query, criteriaBuilder) -> {
      query.distinct(true);
      final Root<Review> review = query.from(Review.class);
      final Root<User> user = query.from(User.class);
      final Join<Object, Object> role = user.join("roles", JoinType.INNER);
      return criteriaBuilder.and(
          criteriaBuilder.equal(review.get("user"), user),
          criteriaBuilder.equal(review.get("item"), root),
          criteriaBuilder.equal(role.get("name"), ERole.ADMIN)
      );
    };
  }

  private static Subquery<Review> reviewRatingSubquery(Root<Item> root, CriteriaQuery<?> query,
                                                       CriteriaBuilder criteriaBuilder) {
    final Subquery<Review> reviewSubquery = query.subquery(Review.class);
    final Root<Review> fromReview = reviewSubquery.from(Review.class);
    return reviewSubquery.select(fromReview).where(criteriaBuilder.and(
        criteriaBuilder.equal(fromReview.get("item"), root),
        criteriaBuilder.equal(fromReview.get("rating"), EXCELENT)
        )
    );
  }

  public static Specification<Item> specificationsFromFilter(ItemFilterRequestDto filter) {
    final Specification<Object> spec = (root, query, cb) -> cb.and();

    return null;
  }
}
