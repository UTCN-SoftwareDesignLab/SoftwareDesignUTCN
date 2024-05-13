package org.example.spring1.item.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.spring1.item.model.Item;
import org.example.spring1.item.model.QItem;

import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryQueryDslImpl implements ItemRepositoryQueryDsl {

  private final EntityManager entityManager;

  @Override
  public List<Item> allItemsWithAtLeastOneReviewRated(int rating) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QItem item = QItem.item;

    return queryFactory.selectFrom(item)
        .where(item.reviews.any().rating.goe(rating))
        .fetch();
  }


}
