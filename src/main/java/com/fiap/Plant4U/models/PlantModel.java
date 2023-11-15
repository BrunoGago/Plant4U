package com.fiap.Plant4U.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import org.springframework.hateoas.Link;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String nome;
    private String urlImagem;
    private String frequenciaRega;
    private int intervaloTempo;
    private boolean notificacaoRega;
}