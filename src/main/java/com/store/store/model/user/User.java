package com.store.store.model.user;

import com.store.store.model.cart.Cart;
import com.store.store.model.product.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_email_uq", columnNames = "email"))
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    @SequenceGenerator(name = "seq_users", initialValue = 10, allocationSize = 10)
    private long id;

    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "author")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();
}
