package com.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.store.model.user.Address;


public record AddressDTO(@JsonIgnore long id, String city, String street, long houseNumber, Address.HouseType houseType,
                         long flatNumber, String zipcode) {
    public static AddressDTO convertEntityToDTO(Address entity) {
        return new AddressDTO(entity.getId(),
                entity.getCity(),
                entity.getStreet(),
                entity.getHouseNumber(),
                entity.getHouseType(),
                entity.getFlatNumber(),
                entity.getZipcode()
        );
    }

    public static Address convertDTOToEntity(AddressDTO dto) {
        Address address = new Address();
        address.setId(dto.id);
        address.setCity(dto.city);
        address.setStreet(dto.street);
        address.setHouseNumber(dto.houseNumber);
        address.setHouseType(dto.houseType);
        address.setFlatNumber(dto.flatNumber);
        address.setZipcode(dto.zipcode);
        return null;
    }
}
