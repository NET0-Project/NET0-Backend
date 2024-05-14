package com.example.net0backend.service;

import com.example.net0backend.dto.ItemStoreResponse;
import com.example.net0backend.entity.Item;
import com.example.net0backend.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;

    public List<ItemStoreResponse> getItemsByStore(Long storeId){
        List<Item> items = itemRepository.findItemByStore(storeId);
        return items.stream()
                .map(item -> ItemStoreResponse.toDto(item))
                .collect(Collectors.toList());
    }
}
