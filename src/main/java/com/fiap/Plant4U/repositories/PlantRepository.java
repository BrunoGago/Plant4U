package com.fiap.Plant4U.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.Plant4U.models.PlantModel;

public interface PlantRepository extends JpaRepository<PlantModel, UUID> {

}
