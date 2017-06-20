package com.citobi.leasing.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;

    public Brand() {    }

    public Brand(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
