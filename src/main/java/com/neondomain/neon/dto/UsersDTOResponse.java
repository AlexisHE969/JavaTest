package com.neondomain.neon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTOResponse {
    private String user;
    private String name;
    private String lastname;
    private Byte age;
}
