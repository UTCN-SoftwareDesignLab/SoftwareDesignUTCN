package org.example.spring1.item.persistence;

import org.example.spring1.item.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositoryQueryDsl {

  List<Item> allItemsWithAtLeastOneReviewRated(int rating);

}
