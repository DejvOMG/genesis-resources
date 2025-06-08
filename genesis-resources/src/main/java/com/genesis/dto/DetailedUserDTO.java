package com.genesis.dto;

import lombok.Data;

@Data
public class DetailedUserDTO {
    private Long id;
    private String name;
    private String surname;
    private String personId;
    private String uuid;
    private String email;
    private Integer age;
}
