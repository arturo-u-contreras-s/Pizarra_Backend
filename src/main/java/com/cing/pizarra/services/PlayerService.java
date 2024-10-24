package com.cing.pizarra.services;

import java.util.List;
import java.util.Optional;

import com.cing.pizarra.domain.entities.PlayerEntity;

public interface PlayerService {
    PlayerEntity savePlayer(PlayerEntity player);
    List<PlayerEntity> findAllPlayers();
    Optional<PlayerEntity> getPlayerById(Long id);
    void deleteById(Long id);
    boolean isExists(Long id);
}