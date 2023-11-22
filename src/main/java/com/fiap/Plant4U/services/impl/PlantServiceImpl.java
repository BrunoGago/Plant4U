package com.fiap.Plant4U.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.fiap.Plant4U.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fiap.Plant4U.models.PlantModel;
import com.fiap.Plant4U.repositories.PlantRepository;
import com.fiap.Plant4U.services.PlantService;

@Service
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantRepository repository;
    @Override
    public PlantModel registerPlant(PlantModel plant) {
        return repository.save(plant);
    }

    @Override
    public Optional<PlantModel> findById(Long plantId) {
        return repository.findById(plantId);
    }

    @Transactional
    @Override
    public void deletePlant(PlantModel plantModel) {
        repository.delete(plantModel);
    }

    @Transactional
    @Override
    public PlantModel updatePlant(PlantModel plantModel) {
        return repository.save(plantModel);
    }

}
