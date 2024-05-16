package com.example.net0backend.repository;

import com.example.net0backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    @Query("select i from Item i where i.shop.id =:storeId")
    List<Item> findItemByStore(@Param("storeId") Long storeId);

}
