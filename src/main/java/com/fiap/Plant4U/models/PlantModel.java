package com.fiap.Plant4U.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PLANTS")
public class PlantModel extends RepresentationModel<PlantModel> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PLANT")
    @SequenceGenerator(name = "SEQ_PLANT", sequenceName = "PLANT_SEQ", allocationSize = 1)
    private Long plantId;

    private String name;
    private String urlImage;
    private String frequencyWatering;
    private String intervalTime;
    private boolean notification;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime lastWatering;

    private Long userId;
}
