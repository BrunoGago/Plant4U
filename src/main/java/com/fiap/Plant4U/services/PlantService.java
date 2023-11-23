package com.fiap.Plant4U.services;

import com.fiap.Plant4U.models.PlantModel;

import java.util.List;
import java.util.Optional;

public interface PlantService {

    PlantModel registerPlant(PlantModel plant);

    Optional<PlantModel> findById(Long plantId);

    void deletePlant(PlantModel plantModel);

    PlantModel updatePlant(PlantModel plantModel);

    List<PlantModel> ListById(Long idUser);

}
