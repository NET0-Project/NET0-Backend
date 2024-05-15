package com.example.net0backend.repository;

import com.example.net0backend.entity.Item;

import java.util.List;

public interface ItemRepositoryCustom {
    List<Item> findItemByStore(Long storeId);
}