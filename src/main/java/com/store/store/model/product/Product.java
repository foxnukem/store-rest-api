package com.store.store.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_products")
    @SequenceGenerator(name = "seq_products", initialValue = 10, allocationSize = 10)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;

    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
