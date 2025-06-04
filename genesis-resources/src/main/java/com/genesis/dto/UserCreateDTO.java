package com.genesis.dto;

import lombok.Data;

@Data
public class UserCreateDTO {
    private String personId;
    private String name;
    private String email;
    private Integer age;
}
