package com.example.net0backend.entity;

import com.example.net0backend.entity.metadata.BaseTimeEntity;
import com.example.net0backend.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class Users extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
