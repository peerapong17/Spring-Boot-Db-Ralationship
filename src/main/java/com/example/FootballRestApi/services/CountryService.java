package com.example.FootballRestApi.services;

import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;

import java.util.List;

public interface CountryService {
    List<Country> getCountries();
    Country getCountryById(Long id);
    void createCountry(Country country);
    void deleteCountry(Long id);
    void updateCountry(Long id, Country country);
}
