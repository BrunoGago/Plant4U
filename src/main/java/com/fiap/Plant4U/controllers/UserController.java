package com.fiap.Plant4U.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fiap.Plant4U.dtos.UserDto;
import com.fiap.Plant4U.models.UserModel;
import com.fiap.Plant4U.services.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") Long userId) {

        log.debug("DELETE deleteUser userId received {}", userId);
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        } else {
            userService.deleteUser(userModelOptional.get());

            log.debug("DELETE deleteUser userId deleted {}", userId);
            log.info("User deleted success! userId {}", userId);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") Long userId,
            @RequestBody UserDto userDto) {

        log.debug("PUT updatePassword userDto received {}", userDto.toString());

        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuário não encontrado!");
        } else if (userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
            var userModel = userModelOptional.get();
            userModel.setPassword(userDto.getPassword());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

            userService.updatePassword(userModel);

            log.debug("PUT updatePassword userDto saved {}", userModel.toString());
            log.info("User password updated successfully userId {}", userModel.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada com sucesso!");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senha informada não condiz com a senha já cadastrada!");
        }
    }
}
