package com.store.store.dto;

import jakarta.validation.constraints.NotNull;

public record UserCredentialsDTO(@NotNull String username, @NotNull String password) {
}
