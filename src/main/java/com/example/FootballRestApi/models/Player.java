package com.example.FootballRestApi.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonBackReference
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="club_id", nullable=true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonBackReference
    private Club club;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "players_positions",
            joinColumns = {@JoinColumn(name = "player_id")},
            inverseJoinColumns = {@JoinColumn(name = "position_id")})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    private Set<Position> positions;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Club getClub() {
        return club;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }
}
