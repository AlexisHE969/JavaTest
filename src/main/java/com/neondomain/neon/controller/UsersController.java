package com.neondomain.neon.controller;

import com.neondomain.neon.dto.UsersDTO;
import com.neondomain.neon.dto.UsersDTOResponse;
import com.neondomain.neon.mapper.UsersMapper;
import com.neondomain.neon.service.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersServices usersServices;

    @PostMapping("/create")
    public ResponseEntity<UsersDTOResponse> save(@Valid @RequestBody UsersDTO dto){
        UsersDTOResponse dtoData = usersServices.saveData(dto);
        return new ResponseEntity<>(dtoData, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UsersDTOResponse> save(@PathVariable Long id, @Valid @RequestBody UsersDTO dto){
        UsersDTOResponse dtoData = usersServices.updateData(dto, id);
        return new ResponseEntity<>(dtoData, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        usersServices.deleteById(id);

        return new ResponseEntity<>("User deleted succesfull",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsersDTOResponse>> getAll(){
        List<UsersDTOResponse> list = usersServices.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTOResponse> getById(@PathVariable Long id){
        UsersDTOResponse dto = usersServices.getOneById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
