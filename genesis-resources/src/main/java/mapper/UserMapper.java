package com.genesis.mapper;

import com.genesis.dto.*;
import com.genesis.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonId(dto.getPersonId());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }

    public BasicUserDTO toBasicDto(User user) {
        BasicUserDTO dto = new BasicUserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

    public DetailedUserDTO toDetailedDto(User user) {
        DetailedUserDTO dto = new DetailedUserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setPersonId(user.getPersonId());
        dto.setUuid(user.getUuid());
        dto.setEmail(user.getEmail());
        dto.setAge(user.getAge());
        return dto;
    }
}
