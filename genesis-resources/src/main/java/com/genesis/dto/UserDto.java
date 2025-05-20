package com.genesis.dto;

import com.genesis.model.User;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String personId;
    private String uuid;

    public static UserDto fromEntity(User user) {
        return fromEntity(user, true);
    }

    public static UserDto fromEntity(User user, boolean detail) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        if (detail) {
            dto.setPersonId(user.getPersonId());
            dto.setUuid(user.getUuid());
        }
        return dto;
    }
}
