package com.fiap.Plant4U.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private Long id;

    private String name;
    private String urlImage;
    private String frequencyWatering;
    private int intervalTime;
    private boolean notification;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "TB_PLANTS_USERS", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserModel> user = new HashSet<>();
}
