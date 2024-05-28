package com.example.net0backend.repository;

import com.example.net0backend.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("SELECT shop from Shop shop where (shop.latitude BETWEEN :x-1.0 and :x+1.0) and (shop.longitude BETWEEN :y-1.0 and :y+1.0)")
    List<Shop> findNearShopList(@Param("x") Double x, @Param("y") Double y);

    @Query("SELECT s FROM Shop s where s.id = :shopId")
    Optional<Shop> findShopById(@Param("shopId") Long shopId);
}
