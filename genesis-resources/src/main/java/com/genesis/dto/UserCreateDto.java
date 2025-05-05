package com.genesis.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateDto {

    @NotBlank(message = "Jméno nesmí být prázdné")
    private String name;

    @NotBlank(message = "Příjmení nesmí být prázdné")
    private String surname;

    @NotBlank(message = "PersonID nesmí být prázdné")
    private String personId;
}
