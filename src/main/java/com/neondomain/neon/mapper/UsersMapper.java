package com.neondomain.neon.mapper;

import com.neondomain.neon.dto.UsersDTO;
import com.neondomain.neon.dto.UsersDTOResponse;
import com.neondomain.neon.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper {
    private final ModelMapper modelMapper;

    public UsersMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public Users convertDtoEntity(UsersDTO dto){
        return modelMapper.map(dto, Users.class);
    }

    public UsersDTOResponse convertEntityDto(Users entity){
        return modelMapper.map(entity, UsersDTOResponse.class);
    }

    public UsersDTO convertEntityDtoLog(Users entity){
        return modelMapper.map(entity, UsersDTO.class);
    }
}
