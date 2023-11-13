package com.fiap.Plant4U.services;

import com.fiap.Plant4U.enums.RoleType;
import com.fiap.Plant4U.models.RoleModel;

import java.util.Optional;
public interface RoleService {

    Optional<RoleModel> findByRoleName(RoleType roleType);
}