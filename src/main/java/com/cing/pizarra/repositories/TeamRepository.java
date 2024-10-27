package com.cing.pizarra.repositories;

import org.springframework.stereotype.Repository;

import com.cing.pizarra.domain.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long> { }
