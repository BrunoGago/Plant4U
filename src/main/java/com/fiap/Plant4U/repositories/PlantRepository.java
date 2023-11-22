package com.fiap.Plant4U.repositories;

import com.fiap.Plant4U.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.Plant4U.models.PlantModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<PlantModel, Long> {

}
