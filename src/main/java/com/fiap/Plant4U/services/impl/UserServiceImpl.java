package com.fiap.Plant4U.services.impl;

import com.fiap.Plant4U.models.UserModel;
import com.fiap.Plant4U.repositories.UserRepository;
import com.fiap.Plant4U.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserModel> findAll() {

        return userRepository.findAll(); // método pronto de JpaRepository
    }

    @Override
    public Optional<UserModel> findById(Long userId) {

        return userRepository.findById(userId);
    }

    @Transactional
    @Override
    public void delete(UserModel userModel) {
        userRepository.delete(userModel);
    }

    @Override
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
        return userRepository.findAll(spec, pageable);
    }

    // Método customizado de Save User para utilizar na mensageria RabbitMQ
    @Transactional
    @Override
    public UserModel saveUser(UserModel userModel) {
        userModel = save(userModel);
        return userModel;
    }

    @Transactional
    @Override
    public void deleteUser(UserModel userModel) {
        delete(userModel);
    }

    @Transactional
    @Override
    public UserModel updateUser(UserModel userModel) {
        userModel = save(userModel);// salva o usuario "atualizado"
        return userModel;
    }

    @Override
    public UserModel updatePassword(UserModel userModel) {
        return save(userModel);
    }


    @Override
    Optional<UserModel> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
