package com.store.store.dto;

import com.store.store.model.product.Category;

public record CategoryDTO(String name) {
    public static CategoryDTO convertEntityToDTO(Category entity) {
        return new CategoryDTO(entity.getName());
    }

    public static Category convertDTOToEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.name);
        return category;
    }
}
