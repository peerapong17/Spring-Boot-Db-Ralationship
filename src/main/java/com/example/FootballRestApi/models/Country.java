package com.example.FootballRestApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="continent_id", nullable=true)
//    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonIgnore
    private Continent continent;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
//    @JsonManagedReference
//    @JsonIgnore
    private Set<Club> clubs;

    @OneToMany(mappedBy="country", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
//    @JsonManagedReference
    private Set<Player> players;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
