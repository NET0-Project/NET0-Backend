package com.example.net0backend.entity;

import com.example.net0backend.entity.metadata.BaseTimeEntity;
import com.example.net0backend.enums.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop")
public class Shop extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    private String name;

    private String address;

    private String call;

    private String type;

    @Column(name = "shop_description")
    private String description;

    private Double latitude; //위도

    private Double longitude; //경도

    private String openingHour;

    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Builder
    public Shop(User user, String name, String address, String call, String description, Double latitude, Double longitude, String openingHour, StoreStatus status) {
        this.user = user;
        this.name = name;
        this.address = address;
        this.call = call;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.openingHour = openingHour;
        this.status = status;
    }
}
