package com.cing.pizarra.services.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.repositories.PlayerRepository;
import com.cing.pizarra.repositories.TeamRepository;
import com.cing.pizarra.services.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;
    private TeamRepository teamRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public PlayerEntity savePlayer(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }

    @Override
    public List<PlayerEntity> findAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlayerEntity> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return playerRepository.existsById(id);
    }
}