package com.cing.pizarra.repositories;

import com.cing.pizarra.domain.entities.PlayerPositionEntity;
import com.cing.pizarra.domain.entities.SquadEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerPositionRepository extends CrudRepository<PlayerPositionEntity, Long> {
    List<PlayerPositionEntity> findAllBySquad_squadId(Long squadId);
    void deleteAllBySquad_squadId(Long squadId);
    void deleteAllByPlayerId(Long playerId);
}