package com.store.store.model.product;

import com.store.store.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Product> products;
}
