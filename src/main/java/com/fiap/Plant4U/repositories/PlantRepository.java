package com.fiap.Plant4U.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fiap.Plant4U.models.PlantModel;

public interface PlantRepository extends JpaRepository<PlantModel, Long> {

    @Query(value = "SELECT * FROM TB_PLANTS WHERE USER_ID = :userId", nativeQuery = true)
    List<PlantModel> listPlantByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM TB_PLANTS WHERE USER_ID = :idUser AND PLANT_ID = :plantId", nativeQuery = true)
    Optional<PlantModel> listPlantById(@Param("idUser") Long idUser, @Param("plantId") Long plantId);
}
