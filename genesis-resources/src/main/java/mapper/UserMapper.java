package com.genesis.mapper;

import com.genesis.dto.UserCreateDTO;
import com.genesis.dto.BasicUserDTO;
import com.genesis.dto.DetailedUserDTO;
import com.genesis.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setPersonId(dto.getPersonId());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());                // ← tady musí být
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

    public List<BasicUserDTO> toBasicDtoList(List<User> users) {
        return users.stream().map(this::toBasicDto).collect(Collectors.toList());
    }

    public List<DetailedUserDTO> toDetailedDtoList(List<User> users) {
        return users.stream().map(this::toDetailedDto).collect(Collectors.toList());
    }
}
