package com.example.net0backend.entity;

import com.example.net0backend.entity.metadata.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "shop")
@Setter
@NoArgsConstructor
public class Shop extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Users users;

    private String name;

    private String address;

    private String call;

    @Column(name = "shop_description")
    private String description;

    private double latitude; //위도

    private double longitude; //경도

    private String openingHour;
}
