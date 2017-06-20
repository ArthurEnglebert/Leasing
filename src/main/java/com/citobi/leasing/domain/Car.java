package com.citobi.leasing.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars", uniqueConstraints = {@UniqueConstraint(columnNames = {"numberPlate"})})
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private LockStatus lockStatus = LockStatus.NONE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    @NotNull
    private Model model;

    @NotNull
    private String numberPlate;

    public Car() { }

    public Car(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LockStatus getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public boolean isLocked() {
        return lockStatus != LockStatus.NONE;
    }


    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
