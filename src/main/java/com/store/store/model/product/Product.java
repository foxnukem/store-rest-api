package com.store.store.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255, message = "Product title must be lesser than 255 characters")
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "category_id")
    private Category category;

    @Size(max = 255, message = "Product description must be lesser than 255 characters")
    private String description;

    @Size(max = 255, message = "Product image_url must be lesser than 255 characters")
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
