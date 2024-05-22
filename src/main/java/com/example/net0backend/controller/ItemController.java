package com.example.net0backend.controller;


import com.example.net0backend.dto.ItemStoreResponse;
import com.example.net0backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{storeId}")
    public List<ItemStoreResponse> showItemSearchByStore(@PathVariable Long storeId){
        log.info("storeId = "+storeId);
        return itemService.getItemsByStore(storeId);
    }
}
