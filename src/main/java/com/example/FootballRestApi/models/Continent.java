package com.example.FootballRestApi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy="continent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Set<Country> countries;

    @OneToMany(mappedBy="continent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Set<Club> clubs;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Country> getCountries() {
        return countries;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }
}
