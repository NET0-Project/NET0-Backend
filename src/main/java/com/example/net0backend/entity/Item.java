package com.example.net0backend.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_price")
    private int price;

    private double discountRate;

    private int stock;

    @Column(name = "exp_date")
    private LocalDateTime exp; //유통기한

    @Column(name = "item_description")
    private String description;
}
