package com.cing.pizarra.repositories;

import org.springframework.stereotype.Repository;

import com.cing.pizarra.domain.entities.SquadEntity;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface SquadRepository extends CrudRepository<SquadEntity, Long> { }