package com.neondomain.neon.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {
    @NotNull(message = "LastName can't be null")
    @NotBlank(message = "LastName can't be empty")
    private String user;

    @NotNull(message = "LastName can't be null")
    @NotBlank(message = "LastName can't be empty")
    private String password;
}
