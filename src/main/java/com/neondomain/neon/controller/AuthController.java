package com.neondomain.neon.controller;

import com.neondomain.neon.dto.LoginRequest;
import com.neondomain.neon.dto.UsersDTO;
import com.neondomain.neon.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UsersServices usersServices;

    @PostMapping("/session")
    public ResponseEntity<?> loggin(@Valid @RequestBody LoginRequest request){
        String email = request.getUser();
        String password = request.getPassword();

        UsersDTO dtoData = usersServices.getByEmail(email);

        if (dtoData != null && usersServices.validPassword(password, dtoData.getPassword())){
            return new ResponseEntity<>(dtoData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found or password incorrect", HttpStatus.OK);
        }
    }


}
