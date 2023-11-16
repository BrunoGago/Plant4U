package com.fiap.Plant4U.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fiap.Plant4U.models.PlantModel;

public interface PlantService {

    PlantModel RegisterPlant(PlantModel plant);

    Optional<PlantModel> findById(UUID plantId);

    void deletePlant(PlantModel plantModel);

    Page<PlantModel> findAll(Pageable pageable);

    PlantModel updatePlant(PlantModel plantModel);

}
