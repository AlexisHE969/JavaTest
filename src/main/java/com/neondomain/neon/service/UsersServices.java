package com.neondomain.neon.service;

import com.neondomain.neon.dto.UsersDTO;
import com.neondomain.neon.dto.UsersDTOResponse;
import com.neondomain.neon.entity.Users;
import com.neondomain.neon.exception.UserAlreadyExistsException;
import com.neondomain.neon.mapper.UsersMapper;
import com.neondomain.neon.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServices {

    @Autowired
    UsersRepository usersRepository;

    private final UsersMapper usersMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServices(UsersMapper usersMapper, PasswordEncoder passwordEncoder){
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public UsersDTOResponse saveData(UsersDTO dto){
        boolean isExist = usersRepository.existsByUser(dto.getUser());
        if (isExist){
            throw new UserAlreadyExistsException("This user can't be used");
        }
        dto.encrypPassword();
        Users entityUsers = usersMapper.convertDtoEntity(dto);
        Users entityUsersSaved = usersRepository.save(entityUsers);
        UsersDTOResponse dtoUsers = usersMapper.convertEntityDto(entityUsersSaved);
        return dtoUsers;
    }

    public UsersDTOResponse updateData(UsersDTO dto, Long id){
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        existingUser.setName(dto.getName());
        existingUser.setLastname(dto.getLastname());
        existingUser.setAge(dto.getAge());
        existingUser.setUser(dto.getUser());

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            String passEncrypt = passwordEncoder.encode(dto.getPassword());
            existingUser.setPassword(passEncrypt);
        }

        Users entityUpdate = usersRepository.save(existingUser);

        return usersMapper.convertEntityDto(entityUpdate);
    }

    public List<UsersDTOResponse> getAll(){
        List<Users> entity = usersRepository.findAll();
        return entity.stream()
                .map(usersMapper::convertEntityDto)
                .collect(Collectors.toList());
    }

    public UsersDTOResponse getOneById(Long id){
        Optional<Users> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            Users entity = userOptional.get();
            return usersMapper.convertEntityDto(entity);
        } else {
            throw new UserAlreadyExistsException("Usuario no encontrado con ID: " + id);
            // O puedes devolver un DTO predeterminado o lanzar otra excepción según tus necesidades.
        }
    }

    public UsersDTO getByEmail(String email){
        Users entity = usersRepository.findByUser(email).orElse(null);
        return (entity != null) ? usersMapper.convertEntityDtoLog(entity) : null;
    }

    public void deleteById(Long id){
        usersRepository.deleteById(id);
    }

    public boolean validPassword(String passwordString, String passwordCrypt) {
        return passwordEncoder.matches(passwordString, passwordCrypt);
    }
}
