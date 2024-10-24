package com.cing.pizarra.services;

import java.util.List;

import com.cing.pizarra.domain.entities.PlayerPositionEntity;

public interface PlayerPositionService {
    PlayerPositionEntity createPlayerPosition(PlayerPositionEntity playerPosition);
    List<PlayerPositionEntity> getPPBySquadId(Long squadId);
    void deletePPBySquadId(Long squadId);
    void deletePPByPlayerId(Long playerId);
}