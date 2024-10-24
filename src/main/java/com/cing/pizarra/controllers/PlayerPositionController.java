package com.cing.pizarra.controllers;

import java.util.List;
import java.util.stream.Collectors;
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
    private PlayerPositionService playerPositionService;
    private Mapper<PlayerPositionEntity, PlayerPositionDto> playerPositionMapper;

    public PlayerPositionController(PlayerPositionService playerPositionService, Mapper<PlayerPositionEntity, PlayerPositionDto> playerPositionMapper, Mapper<SquadEntity, SquadDto> squadMapper) {
        this.playerPositionService = playerPositionService;
        this.playerPositionMapper = playerPositionMapper;
    }

    @PostMapping(path = "/player-positions")
    public ResponseEntity<PlayerPositionDto> createPlayerPosition(@RequestBody PlayerPositionDto playerPosition) {
        PlayerPositionEntity playerPositionEntity = playerPositionMapper.mapFrom(playerPosition);
        PlayerPositionEntity savedPlayerPositionEntity =  playerPositionService.createPlayerPosition(playerPositionEntity);
        return new ResponseEntity<>(playerPositionMapper.mapTo(savedPlayerPositionEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/player-positions/{id}")
    public ResponseEntity<List<PlayerPositionDto>> getPPBySquadId(@PathVariable("id") Long id) {
        List<PlayerPositionEntity> playerPositions = playerPositionService.getPPBySquadId(id);
        return new ResponseEntity<>(playerPositions.stream()
                .map(playerPositionMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping(path = "/player-positions/squad/{id}")
    public ResponseEntity<PlayerPositionDto> deletePPBySquadId(@PathVariable("id") Long id) {
        playerPositionService.deletePPBySquadId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/player-positions/player/{id}")
    public ResponseEntity<PlayerPositionDto> deletePPByPlayerId(@PathVariable("id") Long playerId) {
        playerPositionService.deletePPByPlayerId(playerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}