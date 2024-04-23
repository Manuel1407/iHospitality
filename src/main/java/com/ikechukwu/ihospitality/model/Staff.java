package com.ikechukwu.ihospitality.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Builder
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String uuid;

    private LocalDate registrationDate;

    public Staff() {

    }

    public Staff(Long id, String name, String uuid, LocalDate registrationDate) {
        this.id = id;
        this.name = name;
        this.uuid = uuid;
        this.registrationDate = registrationDate;
    }

    public Staff(String name, String uuid, LocalDate now) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

}
