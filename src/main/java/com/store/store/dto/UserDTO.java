package com.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.store.model.user.User;

public record UserDTO(long id, String email, @JsonIgnore String password, String firstName, String lastName,
                      AddressDTO address,
                      String phone) {
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

    //todo
    public static User convertDTOToEntity(UserDTO dto) {
        return null;
    }
}
