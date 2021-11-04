package com.example.FootballRestApi.services;

import com.example.FootballRestApi.models.Club;
import com.example.FootballRestApi.models.Continent;

import java.util.List;

public interface ClubService {
    List<Club> getClubs();
    Club getClubById(Long id);
    void createClub(Club club);
    void deleteClub(Long id);
    void updateClub(Long id, Club club);
}
