package com.cing.pizarra.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.cing.pizarra.services.PlayerService;
import com.cing.pizarra.services.SquadService;
import com.cing.pizarra.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cing.pizarra.mappers.Mapper;
import com.cing.pizarra.services.PlayerPositionService;
import com.cing.pizarra.domain.dto.PlayerPositionDto;
import com.cing.pizarra.domain.dto.SquadDto;
import com.cing.pizarra.domain.entities.PlayerPositionEntity;
import com.cing.pizarra.domain.entities.SquadEntity;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerPositionController {
    private Mapper<PlayerPositionEntity, PlayerPositionDto> playerPositionMapper;
    private PlayerPositionService playerPositionService;
    private PlayerService playerService;
    private SquadService squadService;

    public PlayerPositionController(Mapper<PlayerPositionEntity, PlayerPositionDto> playerPositionMapper, PlayerPositionService playerPositionService, PlayerService playerService, SquadService squadService) {
        this.playerPositionMapper = playerPositionMapper;
        this.playerPositionService = playerPositionService;
        this.playerService = playerService;
        this.squadService = squadService;
    }

    @PostMapping(path = "/player-positions")
    public ResponseEntity<PlayerPositionDto> createPlayerPosition(@RequestBody PlayerPositionDto playerPosition) {
        PlayerPositionEntity playerPositionEntity = playerPositionMapper.mapFrom(playerPosition);
        if (!squadService.isExists(playerPositionEntity.getSquad().getSquadId())) { // check that squad exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PlayerPositionEntity savedPlayerPositionEntity =  playerPositionService.createPlayerPosition(playerPositionEntity);
        return new ResponseEntity<>(playerPositionMapper.mapTo(savedPlayerPositionEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/player-positions/{id}")
    public ResponseEntity<List<PlayerPositionDto>> getPPBySquadId(@PathVariable("id") Long id) {
        if (!squadService.isExists(id)) { // check that squad exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<PlayerPositionEntity> playerPositions = playerPositionService.getPPBySquadId(id);
        return new ResponseEntity<>(playerPositions.stream()
                .map(playerPositionMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/player-positions/squad/{id}")
    public ResponseEntity<PlayerPositionDto> deletePPBySquadId(@PathVariable("id") Long id) {
        if (!squadService.isExists(id)) { // check that squad exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        playerPositionService.deletePPBySquadId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/player-positions/player/{id}")
    public ResponseEntity<PlayerPositionDto> deletePPByPlayerId(@PathVariable("id") Long playerId) {
        if (!playerService.isExists(playerId)) { // check that player exists
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        playerPositionService.deletePPByPlayerId(playerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}