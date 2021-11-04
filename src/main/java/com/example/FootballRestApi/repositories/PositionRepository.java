package com.example.FootballRestApi.repositories;

import com.example.FootballRestApi.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
