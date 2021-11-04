package com.example.FootballRestApi.repositories;

import com.example.FootballRestApi.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
