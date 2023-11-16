package com.neondomain.neon.controller;

import com.neondomain.neon.dto.UsersDTOResponse;
import com.neondomain.neon.exception.UserAlreadyExistsException;
import com.neondomain.neon.service.UsersServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UsersController.class)
public class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersServices usersServices;


}
