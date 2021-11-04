package com.example.FootballRestApi.services.impl;

import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.repositories.ContinentRepository;
import com.example.FootballRestApi.repositories.CountryRepository;
import com.example.FootballRestApi.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ContinentRepository continentRepository;

    @Override
    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new RuntimeException("No country found"));
    }

    @Override
    public void createCountry(Country country) {
        Continent continent = continentRepository.findById(country.getContinent().getId()).orElseThrow(() -> new RuntimeException("No continent found"));
//        continent.getCountries().forEach(item -> item.setContinent(continent));
        country.setContinent(continent);
        countryRepository.save(country);
    }

    @Override
    public void updateCountry(Long id, Country country) {
        Country existingCountry = countryRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found with given id : " + id));

        existingCountry.setName(country.getName());
        existingCountry.setContinent(country.getContinent());

        countryRepository.save(existingCountry);
    }

    @Override
    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new RuntimeException("No country found"));
        countryRepository.deleteById(country.getId());
    }
}
