package com.example.net0backend.entity;

import com.example.net0backend.entity.metadata.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_origin_price")
    private int originPrice;

    @Column(name = "item_price")
    private int price;

    private double discountRate;

    private int stock;

    @Column(name = "exp_date")
    private LocalDate exp;

    @Column(name = "item_description")
    private String description;
}
