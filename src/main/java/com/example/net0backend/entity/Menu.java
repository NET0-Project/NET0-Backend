package com.example.net0backend.entity;

import com.example.net0backend.entity.metadata.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu")
public class Menu extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "menu_name")
    private String name;

    @Column(name = "menu_origin_price")
    private int originPrice;

    @Column(name = "menu_price")
    private int price;

    private double discountRate;

    //stock, exp X

    @Column(name = "menu_description")
    private String description;
}
