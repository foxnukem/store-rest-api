package com.store.store.security;

import com.store.store.model.user.User;
import com.store.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component("userDetailsService")
@RequiredArgsConstructor
public class WebUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userByEmail = userRepository.findUserByEmail(username);
        if (userByEmail.isPresent()) {
            User user = userByEmail.get();
            org.springframework.security.core.userdetails.User.UserBuilder userBuilder = withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User (email=%s) was not found".formatted(username));
    }
}
