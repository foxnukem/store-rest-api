package com.store.store.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Column(nullable = false)
    @Size(min = 1, max = 255, message = "City name cannot be blank")
    private String city;

    @Column(nullable = false)
    @Size(min = 1, max = 255, message = "Street name cannot be blank")
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
