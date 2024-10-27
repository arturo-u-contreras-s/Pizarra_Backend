package com.cing.pizarra.services;

import java.util.List;

import com.cing.pizarra.domain.entities.SquadEntity;

public interface SquadService {
    SquadEntity saveSquad(SquadEntity squadEntity);
    List<SquadEntity> getAllSquads();
    boolean isExists(Long id);
    void deleteSquadById(Long id);
}
