package com.cing.pizarra.services;

import java.util.List;
import java.util.Optional;

import com.cing.pizarra.domain.entities.TeamEntity;

public interface TeamService {
    TeamEntity saveTeam(TeamEntity team);
    List<TeamEntity> getAllTeams();
    Optional<TeamEntity> getTeamById(Long id);
    boolean isExists(Long id);
    void deleteTeamById(Long id);
}