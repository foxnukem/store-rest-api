package com.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.store.store.model.user.Role;
import com.store.store.model.user.User;
import jakarta.validation.constraints.NotNull;

public record UserDTO(long id, @NotNull String email, @NotNull @JsonIgnore String password, @NotNull String firstName, @NotNull String lastName,
                      @JsonProperty("address") AddressDTO address,
                      @NotNull String phone) {
    public static UserDTO convertEntityToDTO(User entity) {
        return new UserDTO(
                entity.getId(),
                entity.getEmail(),
                "",
                entity.getFirstName(),
                entity.getLastName(),
                AddressDTO.convertEntityToDTO(entity.getAddress()),
                entity.getPhone()
        );
    }

    public static User convertDTOToEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.id);
        user.setEmail(dto.email);
        user.setPassword(dto.password);
        user.setRole(Role.USER);
        user.setAddress(AddressDTO.convertDTOToEntity(dto.address));
        user.setFirstName(dto.firstName);
        user.setLastName(dto.lastName);
        return user;
    }
}
