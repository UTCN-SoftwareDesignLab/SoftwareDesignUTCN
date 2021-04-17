package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class ItemSpecifications {

  public static Specification<Item> similarNames(String name) {
    return (Specification<Item>) (root, query, cb) -> cb.like(root.get("name"), name);
  }

  public static Specification<Item> createdAfter(LocalDateTime date) {
    return (Specification<Item>) (root, query, cb) -> cb.greaterThan(root.get("dateCreated"), date);
  }

}
