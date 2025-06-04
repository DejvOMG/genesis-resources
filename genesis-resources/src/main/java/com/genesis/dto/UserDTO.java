package com.genesis.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String personId;
    private String uuid;
    private String name;
    private String email;
    private Integer age;
}
