package com.store.store.model.user;

import com.store.store.model.cart.Cart;
import com.store.store.model.product.Review;
import com.store.store.model.token.Token;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(name = "users_email_uq", columnNames = "email"))
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users")
    @SequenceGenerator(name = "seq_users", initialValue = 10, allocationSize = 10)
    private long id;

    @Email
    @Column(nullable = false)
    @Size(max = 255)
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String lastName;

    @NotBlank
    @Column(nullable = false)
    @Size(max = 255)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Size(max = 255)
    private Role role;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Address address;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(mappedBy = "author")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.PRIVATE)
    @ToString.Exclude
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
