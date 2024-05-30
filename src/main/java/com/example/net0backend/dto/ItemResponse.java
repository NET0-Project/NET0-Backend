package com.example.net0backend.dto;

import com.example.net0backend.entity.Item;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class ItemResponse {
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

    @NotNull(message = "생성 시간은 null이 될 수 없습니다")
    private LocalDateTime createTime;

    @NotNull(message = "수정 시간은 null이 될 수 없습니다")
    private LocalDateTime updateTime;

    public static ItemResponse toDto(Item item){
        return ItemResponse.builder()
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
