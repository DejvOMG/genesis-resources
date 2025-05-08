package com.genesis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "Jméno nesmí být prázdné.")
    private String name;

    @NotBlank(message = "Příjmení nesmí být prázdné.")
    private String surname;

    @NotBlank(message = "PersonID nesmí být prázdné.")
    private String personId;
}
