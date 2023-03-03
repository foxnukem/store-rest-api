package com.store.store.model.product;

import com.store.store.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}