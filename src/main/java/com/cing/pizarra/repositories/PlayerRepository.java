package com.cing.pizarra.repositories;

import org.springframework.stereotype.Repository;

import com.cing.pizarra.domain.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {}