package com.example.FootballRestApi.services.impl;

import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Position;
import com.example.FootballRestApi.repositories.ContinentRepository;
import com.example.FootballRestApi.services.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentServiceImpl implements ContinentService {

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public List<Continent> getContinents() {
        return continentRepository.findAll();
    }

    @Override
    public Continent getContinentById(Long id) {
        return continentRepository.findById(id).orElseThrow(() -> new RuntimeException("No continent found"));
    }

    @Override
    public void createContinent(Continent continent) {
        continentRepository.save(continent);
    }

    @Override
    public void updateContinent(Long id, Continent continent) {
        Continent existingContinent = continentRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found with given id :: " + id));

        existingContinent.setName(continent.getName());

        continentRepository.save(existingContinent);
    }

    @Override
    public void deleteContinent(Long id) {
        Continent continent = continentRepository.findById(id).orElseThrow(() -> new RuntimeException("No continent found"));
        continentRepository.deleteById(continent.getId());
    }
}
