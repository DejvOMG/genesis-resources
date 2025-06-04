package com.genesis.mapper;

import com.genesis.dto.UserCreateDTO;
import com.genesis.dto.UserDTO;
import com.genesis.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setPersonId(user.getPersonId());
        dto.setUuid(user.getUuid());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }

    public User fromCreateDto(UserCreateDTO dto) {
        User user = new User();
        user.setPersonId(dto.getPersonId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }
}
