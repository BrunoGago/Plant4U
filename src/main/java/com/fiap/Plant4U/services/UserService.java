package com.fiap.Plant4U.services;

import com.fiap.Plant4U.models.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

/*
Essa classe é uma interface de Service, somente criando os métodos que devem ser implementados pela camada de serviços
que está criada no mesmo pacote como UserServiceImpl
 */

public interface UserService {

    List<UserModel> findAll();

    Optional<UserModel> findById(Long userId);

    void delete(UserModel userModel);

    UserModel save(UserModel userModel);

    Boolean existsByEmail(String email);

    Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

    UserModel saveUser(UserModel userModel);

    void deleteUser(UserModel userModel);

    UserModel updateUser(UserModel userModel);

    UserModel updatePassword(UserModel userModel);

    Optional<UserModel> findByEmail(String email);
}