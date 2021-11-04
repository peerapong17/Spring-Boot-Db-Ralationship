package com.example.FootballRestApi.services.impl;

import com.example.FootballRestApi.models.Country;
import com.example.FootballRestApi.models.Player;
import com.example.FootballRestApi.models.Position;
import com.example.FootballRestApi.repositories.PositionRepository;
import com.example.FootballRestApi.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElseThrow(()-> new RuntimeException("No position found"));
    }

    @Override
    public void createPosition(Position position) {
        positionRepository.save(position);
    }

    @Override
    public void updatePosition(Long id, Position position) {
        Position existingPosition = positionRepository.findById(id).orElseThrow(
                () -> new RuntimeException(" Employee not found with given id : " + id));

        existingPosition.setName(position.getName());

        positionRepository.save(existingPosition);
    }

    @Override
    public void deletePosition(Long id) {
        Position position = positionRepository.findById(id).orElseThrow(() -> new RuntimeException("No position found"));
        positionRepository.deleteById(position.getId());
    }
}
