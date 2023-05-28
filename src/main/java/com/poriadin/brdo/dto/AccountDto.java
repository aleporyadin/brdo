package com.poriadin.brdo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto
{
    private Long id;
    @NotEmpty
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}