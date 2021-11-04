package com.example.FootballRestApi.services;

import com.example.FootballRestApi.models.Continent;

import java.util.List;

public interface ContinentService {
    List<Continent> getContinents();
    Continent getContinentById(Long id);
    void createContinent(Continent continent);
    void deleteContinent(Long id);
    void updateContinent(Long id, Continent continent);
}
