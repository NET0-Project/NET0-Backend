package com.example.net0backend.service;

import com.example.net0backend.auth.JWTProvider;
import com.example.net0backend.controller.ItemController;
import com.example.net0backend.dto.request.MyLocationRequest;
import com.example.net0backend.dto.response.NearStoreResponse;
import com.example.net0backend.entity.Shop;
import com.example.net0backend.enums.StoreStatus;
import com.example.net0backend.repository.ShopRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class StoreServiceTest {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private StoreService storeService;

    @AfterEach
    void tearDown() {
        shopRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("내 주변에 있는 가게 목록을 가게 상태와 함께 가져온다.")
    void getNearStoreList() {
        //given

        Shop shop1 = createShop("매장1", "서울시 강남구", "010-1234-5678", "매장1 설명",
                37.123456, 127.123456, "09:00 ~ 21:00", StoreStatus.FOOD);
        Shop shop2 = createShop("매장2", "서울시 강남구", "010-1234-5678", "매장2 설명",
                39.123457, 127.123457, "09:00 ~ 21:00", StoreStatus.NOFOOD);
        Shop shop3 = createShop("매장3", "서울시 강남구", "010-1234-5678", "매장3 설명",
                37.12348, 129.12348, "09:00 ~ 10:00", StoreStatus.CLOSE);
        Shop shop4 = createShop("매장3", "서울시 강남구", "010-1234-5678", "매장3 설명",
                37.123456, 127.123457, "09:00 ~ 10:00", StoreStatus.CLOSE);
        shopRepository.saveAll(List.of(shop1, shop2, shop3, shop4));

        MyLocationRequest myLocation = new MyLocationRequest(37.123456, 127.123456);

        //when
        List<NearStoreResponse> nearStoreList = storeService.getNearStoreList(myLocation);

        //then
        assertThat(nearStoreList).hasSize(2)
                .extracting("status", "x", "y")
                .containsExactlyInAnyOrder(
                        tuple(StoreStatus.FOOD, 37.123456, 127.123456),
                        tuple(StoreStatus.CLOSE, 37.123456, 127.123457)
                );
    }

    private Shop createShop(String name, String address, String phone, String description,
                            Double x, Double y, String openHour, StoreStatus status) {
        return Shop.builder()
                .name(name)
                .address(address)
                .call(phone)
                .description(description)
                .latitude(x)
                .longitude(y)
                .openingHour(openHour)
                .status(status)
                .build();
    }
}