package com.cing.pizarra.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cing.pizarra.domain.entities.PlayerPositionEntity;
import com.cing.pizarra.repositories.PlayerPositionRepository;
import com.cing.pizarra.services.PlayerPositionService;

@Service
public class PlayerPositionServiceImpl implements PlayerPositionService {
    public PlayerPositionRepository playerPositionRepository;

    public PlayerPositionServiceImpl(PlayerPositionRepository playerPositionRepository) {
        this.playerPositionRepository = playerPositionRepository;
    }

    @Override
    public PlayerPositionEntity createPlayerPosition(PlayerPositionEntity playerPositionEntity) {
        return playerPositionRepository.save(playerPositionEntity);
    }

    @Override
    public List<PlayerPositionEntity> getPPBySquadId(Long squadId) {
        return StreamSupport.stream(playerPositionRepository.findAllBySquad_squadId(squadId).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePPBySquadId(Long squadId) {
        playerPositionRepository.deleteAllBySquad_squadId(squadId);
    }

    @Override
    @Transactional
    public void deletePPByPlayerId(Long playerId) {
        playerPositionRepository.deleteAllByPlayerId(playerId);
    }
}