package com.example.net0backend.controller;


import com.example.net0backend.dto.ItemResponse;
import com.example.net0backend.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{storeId}")
    public ResponseEntity<Map<String,Object>> showItemSearchByStore(@PathVariable Long storeId){
        //findStoreById 개발 이후 throw StoreNotFoundException 추가하기
        log.info("storeId = "+storeId);
        Map<String, Object> result = new HashMap<>();
        result.put("storeId",storeId);
        result.put("items",itemService.getItemsByStore(storeId));
        return ResponseEntity.ok().body(result);
    }
}
