package com.example.FootballRestApi.services;

import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.models.Player;

import java.util.List;

public interface PlayerService {
    List<Player> getPlayers();
    Player getPlayerById(Long id);
    void createPlayer(Player player);
    void updatePlayer(Long id, Player player);
    void deletePlayer(Long id);
}
