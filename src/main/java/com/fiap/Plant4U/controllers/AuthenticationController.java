package com.fiap.Plant4U.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.configs.security.JwtProvider;
import com.fiap.Plant4U.dtos.JwtDto;
import com.fiap.Plant4U.dtos.LoginDto;
import com.fiap.Plant4U.dtos.UserDto;
import com.fiap.Plant4U.enums.RoleType;
import com.fiap.Plant4U.enums.UserStatus;
import com.fiap.Plant4U.enums.UserType;
import com.fiap.Plant4U.models.RoleModel;
import com.fiap.Plant4U.models.UserModel;
import com.fiap.Plant4U.services.RoleService;
import com.fiap.Plant4U.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup") //Método Post está separado para que o usuário se cadastre primeiro antes de acessar a aplicação
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto){

        log.debug("POST registerUser userDto received {}", userDto.toString());

        if(userService.existsByUsername(userDto.getUsername())){

            log.warn("Error: Username {} is already taken!",  userDto.getUsername());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
        }
        if(userService.existsByEmail(userDto.getEmail())){

            log.warn("Error: Email {} is already taken!", userDto.getEmail());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already taken!");
        }
        RoleModel roleModel = roleService.findByRoleName(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is Not Found."));

        userDto.setPassword(userDto.getPassword());

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel); //O BeanUtil transforma um objeto para outro
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.USER);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.getRoles().add(roleModel);//vai definir o role e salvar na tabela

        userService.saveUser(userModel);//utiliza o novo saveUser para usar a exchange e publicar que um user foi criado

        //Gera os logs com LOG4J2
        log.debug("POST registerUser userId saved {}", userModel.getUserId());
        log.info("User saved successfully userId {}", userModel.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }

}
