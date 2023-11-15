package com.fiap.Plant4U.services.impl;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fiap.Plant4U.models.PlantModel;
import com.fiap.Plant4U.repositories.PlantRepository;
import com.fiap.Plant4U.services.PlantService;

public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository repository;

    @Override
    public PlantModel RegisterPlant(PlantModel plant) {
        return repository.save(plant);
    }

    @Override
    public Optional<PlantModel> findById(UUID plantId) {
        return repository.findById(plantId);
    }

    @Transactional
    @Override
    public void deletePlant(PlantModel plantModel) {
        repository.delete(plantModel);
    }

    @Override
    public Page<PlantModel> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
