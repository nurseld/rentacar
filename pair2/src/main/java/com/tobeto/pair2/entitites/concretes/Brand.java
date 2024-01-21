package com.tobeto.pair2.entitites.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tobeto.pair2.entitites.abstracts.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "brands")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends BaseEntity {


    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Model> models;

}
