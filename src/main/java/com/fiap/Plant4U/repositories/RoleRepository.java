package com.fiap.Plant4U.repositories;

import com.fiap.Plant4U.enums.RoleType;
import com.fiap.Plant4U.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
    Optional<RoleModel> findByRoleName(RoleType name);

}
