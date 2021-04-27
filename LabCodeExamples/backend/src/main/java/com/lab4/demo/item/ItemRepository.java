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

    List<Item> findAllByNameOrDescription(String name, String description);

    List<Item> findAllByNameContainingOrDescriptionContaining(String name, String description);

    List<Item> findAllByNameLikeOrDescriptionLike(String name, String description);

    List<Item> findAllByNameLikeOrderByNameDesc(String name);

    List<Item> findAllByNameLike(String name, Sort sorting);

    Page<Item> findAllByNameLike(String name, Pageable pageable);

}
