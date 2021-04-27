package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import com.lab4.demo.item.model.dto.ItemFilterRequestDto;
import com.lab4.demo.review.model.Review;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import java.time.LocalDateTime;

import static com.lab4.demo.review.Rating.EXCELLENT;
import static java.util.Optional.ofNullable;

public class ItemSpecifications {

    public static Specification<Item> similarNames(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Item> createdAfter(LocalDateTime date) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dateCreated"), date);
    }

    public static Specification<Item> onlyExcellentRated() {
        return (itemRoom, query, criteriaBuilder) -> {

            Subquery<Review> reviewSubquery = query.subquery(Review.class);
            Root<Review> reviewRoot = reviewSubquery.from(Review.class);

            Subquery<Review> reviewCriteria = reviewSubquery.select(reviewRoot).where(
                    criteriaBuilder.and(
                            criteriaBuilder.equal(reviewRoot.get("item"), itemRoom),
                            criteriaBuilder.equal(reviewRoot.get("rating"), EXCELLENT)
                    )
            );

            return criteriaBuilder.exists(reviewCriteria);
        };
    }

    public static Specification<Item> withAdministratorReviews() {
        return (itemRoot, query, criteriaBuilder) -> {
            Root<Review> review = query.from(Review.class);
            Root<User> user = query.from(User.class);
            Join<Object, Object> role = user.join("roles", JoinType.INNER);

            return criteriaBuilder.and(
                    criteriaBuilder.equal(review.get("item"), itemRoot),
                    criteriaBuilder.equal(review.get("user"), user),
                    criteriaBuilder.equal(role.get("name"), ERole.ADMIN)
            );
        };
    }


    public static Specification<Item> specificationsFromFilter(ItemFilterRequestDto filter) {
        final Specification<Item> spec = (root, query, criteriaBuilder) -> criteriaBuilder.and();

//        if(filter.getName() != null) spec.and(similarNames(filter.getName()));
        ofNullable(filter.getName()).ifPresent(name -> spec.and(similarNames(name)));
        ofNullable(filter.getCreatedAfter()).ifPresent(date -> spec.and(createdAfter(date)));
        if (filter.getOnlyExcellent()) spec.and(onlyExcellentRated());
        if (filter.getWithAdminReview()) spec.and(withAdministratorReviews());

        return spec;
    }
}
