package com.example.FootballRestApi.services;

import com.example.FootballRestApi.models.Player;
import com.example.FootballRestApi.models.Position;

import java.util.List;

public interface PositionService {
    List<Position> getPositions();
    Position getPositionById(Long id);
    void createPosition(Position position);
    void updatePosition(Long id, Position position);
    void deletePosition(Long id);
}
