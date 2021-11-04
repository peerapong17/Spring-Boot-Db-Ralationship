package com.example.FootballRestApi.services.impl;

import com.example.FootballRestApi.models.Club;
import com.example.FootballRestApi.models.Continent;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.repositories.ClubRepository;
import com.example.FootballRestApi.repositories.ContinentRepository;
import com.example.FootballRestApi.repositories.CountryRepository;
import com.example.FootballRestApi.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ContinentRepository continentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    @Override
    public Club getClubById(Long id) {
        return clubRepository.findById(id).orElseThrow(() -> new RuntimeException("No club found"));
    }

    @Override
    public void createClub(Club club) {
        Continent continent = continentRepository.findById(club.getContinent().getId()).orElseThrow(() -> new RuntimeException("No continent found"));
        Country country = countryRepository.findById(club.getCountry().getId()).orElseThrow(() -> new RuntimeException("No country found"));

        club.setContinent(continent);
        club.setCountry(country);

        clubRepository.save(club);
    }

    @Override
    public void updateClub(Long id, Club club) {
        Club existingClub = clubRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found with given id :: " + id));

        existingClub.setName(club.getName());
        existingClub.setCountry(club.getCountry());
        existingClub.setContinent(club.getContinent());

        clubRepository.save(existingClub);
    }

    @Override
    public void deleteClub(Long id) {
        Club club = clubRepository.findById(id).orElseThrow(() -> new RuntimeException("No club found"));
        clubRepository.deleteById(club.getId());
    }
}
