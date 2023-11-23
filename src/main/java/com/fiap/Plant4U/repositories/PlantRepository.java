package com.fiap.Plant4U.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fiap.Plant4U.models.PlantModel;

public interface PlantRepository extends JpaRepository<PlantModel, Long> {

    @Query(value = "SELECT * FROM TB_PLANTS WHERE TBP.USER_ID = :idUser ", nativeQuery = true)
    List<PlantModel> ListById(Long idUser);
}
