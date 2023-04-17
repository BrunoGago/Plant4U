package com.fiap.Plant4U.authuser.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.authuser.dtos.UserDto;
import com.fiap.Plant4U.authuser.enums.UserStatus;
import com.fiap.Plant4U.authuser.models.UserModel;
import com.fiap.Plant4U.authuser.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Logger;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

	private static final Logger logger = Logger.getLogger(AuthenticationController.class.getName());
	
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody
                                               @Validated(UserDto.UserView.RegistrationPost.class)
                                               @JsonView(UserDto.UserView.RegistrationPost.class)UserDto userDto){

    	logger.warning("fdsafasdfasd");
    	
        if(userService.existsByUsername(userDto.getUsername())){
        	logger.warning("Error: Username {} is already taken!" + userDto.getUsername());
//            log.warn("Error: Username {} is already taken!",  userDto.getUsername());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
        } else if (userService.existsByEmail(userDto.getEmail())) {
        	logger.warning("Error: Email {} is already taken!" + userDto.getEmail());
//            log.warn("Error: Email {} is already taken!", userDto.getEmail());

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already taken!");
        }
        else {
            var userModel = new UserModel();
            BeanUtils.copyProperties(userDto, userModel);//conversão de DTO para model
            userModel.setUserStatus(UserStatus.ACTIVE);
            userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

            userService.save(userModel);

            return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
        }
    }
}
