package com.store.store.model.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
public class Address {
    public enum HouseType {FLAT, HOUSE}

    @Id
    private long id;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private long houseNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    private long flatNumber;

    @Column(nullable = false)
    private String zipcode;
}
