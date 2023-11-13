package com.fiap.Plant4U.services.impl;

import com.fiap.Plant4U.enums.RoleType;
import com.fiap.Plant4U.models.RoleModel;
import com.fiap.Plant4U.repositories.RoleRepository;
import com.fiap.Plant4U.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<RoleModel> findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType);
    }
}
