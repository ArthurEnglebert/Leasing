package com.citobi.leasing.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "models", uniqueConstraints = { @UniqueConstraint(columnNames = {"brand_id", "name"}) })
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    @NotNull
    private Brand brand;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;

    public Model() {    }

    public Model(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }
}
