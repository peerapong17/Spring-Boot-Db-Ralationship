package com.example.FootballRestApi.repositories;

import com.example.FootballRestApi.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
