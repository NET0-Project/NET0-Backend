package com.example.net0backend.repository;

import com.example.net0backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    @Override
    @Query("select i from Item i where i.shop.id = :store_id")
    List<Item> findItemByStore(@Param(value="store_id") Long storeID);
    //만약 해당 가게의 item 정보가 없다면 빈 리스트가 반환되는지 궁금하다!
}
