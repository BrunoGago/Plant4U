package com.fiap.Plant4U.controllers;

import com.fiap.Plant4U.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fiap.Plant4U.models.PlantModel;
import com.fiap.Plant4U.services.PlantService;

import lombok.extern.log4j.Log4j2;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/plants")
public class PlantController {

    @Autowired
    PlantService service;

    @PostMapping("/register/{idUser}")
    public ResponseEntity<String> registerPlant(@RequestBody PlantModel plant,
            @PathVariable(value = "idUser") Long idUser) {

        try {
            plant.setId(idUser);
            service.registerPlant(plant);
            return ResponseEntity.status(HttpStatus.OK).body("Planta registrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{plantId}")
    public ResponseEntity<String> deletePlant(@PathVariable(value = "plantId") Long plantId) {

        log.debug("DELETE deletePlant plantId received {}", plantId);
        var plantModel = service.findById(plantId);

        if (plantModel.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não cadastrada!");
        } else {
            service.deletePlant(plantModel.get());

            log.debug("DELETE deletePlant plantId deleted {}", plantModel);
            log.info("Plant deleted sucess! plantId: {}", plantId);
            return ResponseEntity.status(HttpStatus.OK).body("Planta deletada com sucesso!");
        }
    }

    @GetMapping("/{plantId}")
    public ResponseEntity<Object> getPlantById(@PathVariable(value = "plantId") Long plantId) {
        var result = service.findById(plantId);

        if (!result.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não cadastrada!");
    }

    @GetMapping("/listPlants/{idUser}")
    public ResponseEntity<Object> getAllPlants(@PathVariable(value = "idUser") Long idUser) {
        var result = service.ListById(idUser);

        if (!result.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Planta não cadastrada!");
    }

    @PutMapping("/{plantId}")
    public ResponseEntity<Object> updatePlant(@PathVariable(value = "plantId") Long plantId,
            @RequestBody PlantModel model) {

        log.debug("PUT updatePlant model received {}", model.toString());

        Optional<PlantModel> plant = service.findById(plantId);
        if (!plant.isEmpty()) {
            var plantModel = plant.get();
            plantModel.setName(model.getName());
            plantModel.setUrlImage(model.getUrlImage());
            plantModel.setFrequencyWatering(model.getFrequencyWatering());
            plantModel.setIntervalTime(model.getIntervalTime());
            plantModel.setNotification(model.isNotification());

            service.updatePlant(plantModel);

            log.debug("PUT updatePlant plantId saved {}", plantModel.getId());
            log.debug("Plant updated successfully plantId {}", plantModel.getId());

            return ResponseEntity.status(HttpStatus.OK).body(plantModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(plantId);
        }
    }

    @PostMapping("/registerWatering/{plantId}") // correção: colocar a relação do usuário para a planta, pois senao a
                                                // rega será para todos os usuários
    public ResponseEntity<String> registerLastWatering(@PathVariable(value = "plantId") Long plantId) {

        Optional<PlantModel> plant = service.findById(plantId);
        if (!plant.isEmpty()) {
            var plantModel = plant.get();
            plantModel.setLastWatering(LocalDateTime.now(ZoneId.of("UTC")));

            service.updatePlant(plantModel);

            log.debug("PUT updatePlant plantId saved {}", plantModel.getId());
            log.debug("Plant updated successfully plantId {}", plantModel.getId());

            return ResponseEntity.status(HttpStatus.OK).body("Última rega atualizada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro na atualização da última rega!");
        }
    }

    // CRIAR ENDPOINT: Criar um endpoint que vai trazer as plantas que são de um
    // determinado usuário
}
