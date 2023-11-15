package com.fiap.Plant4U.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.Plant4U.models.PlantModel;
import com.fiap.Plant4U.repositories.PlantRepository;
import com.fiap.Plant4U.services.PlantService;

import lombok.extern.log4j.Log4j2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    private PlantService service;

    @PostMapping("/register")
    public ResponseEntity<String> registerPlant(@RequestBody PlantModel plant) {

        try {
            service.RegisterPlant(plant);
            return ResponseEntity.status(HttpStatus.OK).body("Registered plant!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{plantId}")
    public ResponseEntity<String> deletePlant(@PathVariable(value = "plantId") UUID plantId) {

        log.debug("DELETE deletePlant plantId received {}", plantId);
        var plantModel = service.findById(plantId);

        if (!plantModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not exist!");
        } else {
            service.deletePlant(plantModel.get());

            log.debug("DELETE deletePlant plantId deleted {}", plantModel);
            log.info("Plant deleted sucess! plantId: {}", plantId);
            return ResponseEntity.status(HttpStatus.OK).body("Plant deleted sucessfully!");
        }
    }

    @GetMapping("/{plantId}")
    public ResponseEntity<Object> GetPlantById(@PathVariable(value = "plantId") UUID plantId) {
        var result = service.findById(plantId);
        if (!result.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plant not found!");
    }

    @GetMapping("/listPlants")
    public ResponseEntity<Page<PlantModel>> ListPlants(
            @PageableDefault(page = 0, size = 10, sort = "userId", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<PlantModel> plantModel = service.findAll(pageable);

        if (!plantModel.isEmpty())
            for (PlantModel plant : plantModel.toList()) {
                plant.add(linkTo(methodOn(PlantController.class).GetPlantById(plant.getId())).withSelfRel());
            }

        return ResponseEntity.status(HttpStatus.OK).body(plantModel);
    }
}
