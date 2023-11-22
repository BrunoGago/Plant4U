package com.fiap.Plant4U.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.validations.UsernameConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotBlank()
    private String email;

    @NotBlank()
    @Size(min = 6, max = 20)
    private String password;

    @NotBlank()
    @Size(min = 6, max = 20)
    private String oldPassword;

    private String fullName;

}
