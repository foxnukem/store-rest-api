package com.store.store.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categories")
    @SequenceGenerator(name = "seq_categories", initialValue = 10, allocationSize = 10)
    private long id;

    @Column(nullable = false, unique = true)
    @Size(min = 1, max = 255, message = "Category name must be lesser than 255 characters")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Product> products;
}
