package com.cing.pizarra.services.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.repositories.TeamRepository;
import com.cing.pizarra.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public TeamEntity saveTeam(TeamEntity teamEntity) {
        return teamRepository.save(teamEntity);
    }

    @Override
    public List<TeamEntity> getAllTeams() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TeamEntity> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return teamRepository.existsById(id);
    }

    @Override
    public void deleteTeamById(Long id) {
        teamRepository.deleteById(id);
    }
}