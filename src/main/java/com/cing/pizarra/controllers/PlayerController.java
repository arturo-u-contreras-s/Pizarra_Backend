package com.cing.pizarra.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cing.pizarra.mappers.Mapper;
import com.cing.pizarra.domain.dto.PlayerDto;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.services.PlayerService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {
    private Mapper<PlayerEntity, PlayerDto> playerMapper;
    private PlayerService playerService;

    public PlayerController(Mapper<PlayerEntity, PlayerDto> playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @PostMapping(path = "/players")
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
        PlayerEntity savedPlayerEntity = playerService.savePlayer(playerEntity);
        PlayerDto savedPlayerDto = playerMapper.mapTo(savedPlayerEntity);
        return new ResponseEntity<>(savedPlayerDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/players")
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerEntity> players = playerService.findAllPlayers();
        return new ResponseEntity<>(players.stream().map(playerMapper::mapTo).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/players/{id}")
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable("id") Long id) {
        Optional<PlayerEntity> foundPlayer = playerService.getPlayerById(id);
        return foundPlayer.map(playerEntity -> {
            PlayerDto playerDto = playerMapper.mapTo(playerEntity);
            return new ResponseEntity<>(playerDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/players/{id}")
    public ResponseEntity<PlayerDto> fullPlayerUpdate(@PathVariable("id") Long id, @RequestBody PlayerDto playerDto) {
        if (!playerService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PlayerEntity playerEntity = playerMapper.mapFrom(playerDto);
        PlayerEntity savedPlayerEntity = playerService.savePlayer(playerEntity);
        PlayerDto savedPlayerDto = playerMapper.mapTo(savedPlayerEntity);
        return new ResponseEntity<>(savedPlayerDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "/players/{id}")
    public ResponseEntity<PlayerDto> deleteById(@PathVariable("id") Long id) {
        playerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}