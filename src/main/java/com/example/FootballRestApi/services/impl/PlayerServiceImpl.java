package com.example.FootballRestApi.services.impl;

import com.example.FootballRestApi.models.Club;
import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.models.Player;
import com.example.FootballRestApi.models.Position;
import com.example.FootballRestApi.repositories.ClubRepository;
import com.example.FootballRestApi.repositories.CountryRepository;
import com.example.FootballRestApi.repositories.PlayerRepository;
import com.example.FootballRestApi.repositories.PositionRepository;
import com.example.FootballRestApi.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private PositionRepository positionRepository;


    @Override
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElseThrow(()-> new RuntimeException("No player found"));
    }

    @Override
    public void createPlayer(Player player) {
        Country country = countryRepository.findById(player.getCountry().getId()).orElseThrow(() -> new RuntimeException("No country found"));
        Club club = clubRepository.findById(player.getClub().getId()).orElseThrow(() -> new RuntimeException("No club found"));

        player.setCountry(country);
        player.setClub(club);

        player.getPositions().forEach((item) -> {
            Position position = positionRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException("No position found"));
            position.getPlayers().add(player);

            // cause the problem
//            positionRepository.save(position);
        });

        playerRepository.save(player);
    }

    @Override
    public void updatePlayer(Long id, Player updatedPlayer) {
        Player existingPlayer = playerRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found with given id :: " + id));

        existingPlayer.setName(updatedPlayer.getName());
        existingPlayer.setCountry(updatedPlayer.getCountry());
        existingPlayer.setClub(updatedPlayer.getClub());

        updatedPlayer.getPositions().forEach((item) -> {
            if(existingPlayer.getPositions().contains(item)) {
                existingPlayer.getPositions().forEach((position) -> {
                    if(!updatedPlayer.getPositions().contains(position)){
                        existingPlayer.getPositions().removeIf((u) -> u.getId() == position.getId());
                    }
                });
            }  else {
                existingPlayer.getPositions().add(item);
            }
        });

        playerRepository.save(existingPlayer);
    }


    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new RuntimeException("No player found"));
        playerRepository.deleteById(player.getId());
    }
}
