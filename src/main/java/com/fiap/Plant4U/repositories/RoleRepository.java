package com.fiap.Plant4U.repositories;

import com.fiap.Plant4U.enums.RoleType;
import com.fiap.Plant4U.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByRoleName(RoleType name);
}
