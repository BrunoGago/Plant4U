package com.fiap.Plant4U.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.dtos.LoginDto;
import com.fiap.Plant4U.dtos.UserDto;
import com.fiap.Plant4U.enums.UserStatus;
import com.fiap.Plant4U.enums.UserType;
import com.fiap.Plant4U.models.UserModel;
import com.fiap.Plant4U.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){

        log.debug("POST registerUser userDto received {}", userDto.toString());

        if(userService.existsByEmail(userDto.getEmail())){

            log.warn("Error: Email {} is already taken!", userDto.getEmail());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: Email já cadastrado!");
        }

        userDto.setPassword(userDto.getPassword());

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.saveUser(userModel);

        log.debug("POST registerUser userId saved {}", userModel.getUserId());
        log.info("User saved successfully userId {}", userModel.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @PostMapping("/login/{userId}") //ALTERAR na Repository (criar uma chamada pro banco para validar email e senha)
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginDto loginDto, @PathVariable(value = "userId") Long userId) {

        var userModel = new UserModel();
        Boolean userModelBoolean = userService.existsByEmail(loginDto.getEmail());
        if (userModelBoolean == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        } else if (userModel.getPassword().equals(loginDto.getPassword()) &&
                userModelBoolean.get().getEmail().equals(loginDto.getEmail())) {
            return ResponseEntity.status(HttpStatus.OK).body("Login bem sucedido");
        }
        else {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("senha e/ou email errados!");
        }
        return null;
    }

}
