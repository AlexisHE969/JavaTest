package com.neondomain.neon.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be empty")
    @Size(max = 250, message = "Max length is 250")
    @Column(unique = true)
    private String user;

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be empty")
    @Size(max = 80, message = "Max length is 80")
    private String name;

    @NotNull(message = "LastName can't be null")
    @NotBlank(message = "LastName can't be empty")
    @Size(max = 80, message = "Max length is 80")
    private String lastname;

    @NotNull(message = "age can't be null")
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 90, message = "Max value is 90")
    private Byte age;

    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be empty")
    @Size(max = 250, message = "Max length is 250")
    private String password;

    public void encrypPassword(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(this.password);
    }
}
