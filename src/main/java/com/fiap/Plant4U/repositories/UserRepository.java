package com.fiap.Plant4U.repositories;

import com.fiap.Plant4U.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
    boolean existsByEmail(String email);

    Optional<UserModel> findById(Long userId);

    @Query(value = "SELECT * FROM tb_users tbu WHERE tbu.email= :email", nativeQuery = true)
    Optional<UserModel> findByEmail(@Param("email") String email);
}
