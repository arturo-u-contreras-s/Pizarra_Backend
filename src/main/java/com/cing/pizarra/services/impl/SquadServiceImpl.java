package com.cing.pizarra.services.impl;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.cing.pizarra.domain.entities.SquadEntity;
import com.cing.pizarra.repositories.SquadRepository;
import com.cing.pizarra.services.SquadService;

@Service
public class SquadServiceImpl implements SquadService {
    public SquadRepository squadRepository;

    public SquadServiceImpl(SquadRepository squadRepository) {
        this.squadRepository = squadRepository;
    }

    @Override
    public SquadEntity saveSquad(SquadEntity squadEntity) {
        return squadRepository.save(squadEntity);
    }

    @Override
    public List<SquadEntity> getAllSquads() {
        return StreamSupport.stream(squadRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return squadRepository.existsById(id);
    }

    @Override
    public void deleteSquadById(Long id) {
        squadRepository.deleteById(id);
    }
}