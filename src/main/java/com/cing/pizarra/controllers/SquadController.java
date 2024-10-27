package com.cing.pizarra.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cing.pizarra.domain.dto.SquadDto;
import com.cing.pizarra.domain.entities.SquadEntity;
import com.cing.pizarra.mappers.Mapper;
import com.cing.pizarra.services.SquadService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SquadController {
    private SquadService squadService;
    private Mapper<SquadEntity, SquadDto> squadMapper;

    public SquadController(SquadService squadService, Mapper<SquadEntity, SquadDto> squadMapper) {
        this.squadService = squadService;
        this.squadMapper = squadMapper;
    }

    @PostMapping(path = "/squads")
    public ResponseEntity<SquadDto> createSquad(@RequestBody SquadDto squad) {
        SquadEntity squadEntity = squadMapper.mapFrom(squad);
        SquadEntity savedSquadEntity =  squadService.saveSquad(squadEntity);
        return new ResponseEntity<>(squadMapper.mapTo(savedSquadEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/squads")
    public ResponseEntity<List<SquadDto>> getAllSquads() {
        List<SquadEntity> squads = squadService.getAllSquads();
        return new ResponseEntity<>(squads.stream()
                .map(squadMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @PutMapping(path = "squads/{id}")
    public ResponseEntity<SquadDto> fullSquadUpdate(@PathVariable("id") Long id, @RequestBody SquadDto squadDto) {
        if (!squadService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SquadEntity squadEntity = squadMapper.mapFrom(squadDto);
        SquadEntity savedSquadEntity = squadService.saveSquad(squadEntity);
        SquadDto savedSquadDto = squadMapper.mapTo(savedSquadEntity);
        return new ResponseEntity<>(savedSquadDto, HttpStatus.OK);
    }

    @DeleteMapping(path = "squads/{id}")
    public ResponseEntity<SquadDto> deleteSquadById(@PathVariable("id") Long id) {
        if (!squadService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        squadService.deleteSquadById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}