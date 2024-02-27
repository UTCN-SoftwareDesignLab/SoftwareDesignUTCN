package com.lab4.demo.item;

import com.lab4.demo.item.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    List<Item> findAllByNameLikeOrDescriptionLike(String name, String description);

    List<Item> findAllByNameLikeOrderByNameDesc(String name);

    // or, more dynamically...
    List<Item> findAllByNameLike(String name, Sort sorting);

    Page<Item> findAllByNameLike(String name, Pageable pageable);

    Page<Item> findAllByDescriptionLike(String description, Pageable pageable);

    // what if we had 5+ fields to search on...?
    // problem with the fixed set of criterias
    // wouldn't it be cool to have a set of atomic predicates to combine at will?
}
