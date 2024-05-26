package com.example.net0backend.dto;

import com.example.net0backend.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ItemStoreResponse {
    @NotNull(message = "상품 ID는 null이 될 수 없습니다")
    private Long itemId;

    @NotNull(message = "매장 ID는 null이 될 수 없습니다")
    private Long shopId;

    private String itemName;
    private int originPrice;
    private int price;
    private double discountRate;
    private int stock;
    private LocalDate expDate;
    private String description;

    public static ItemStoreResponse toDto(Item item){
        return ItemStoreResponse.builder()
                .itemId(item.getId())
                .shopId(item.getShop().getId())
                .itemName(item.getName())
                .originPrice(item.getOriginPrice())
                .price(item.getPrice())
                .discountRate(item.getDiscountRate())
                .stock(item.getStock())
                .expDate(item.getExp())
                .description(item.getDescription())
                .build();
    }
}
