package com.cing.pizarra.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cing.pizarra.domain.dto.TeamDto;
import com.cing.pizarra.domain.entities.TeamEntity;
import com.cing.pizarra.mappers.Mapper;
import com.cing.pizarra.services.TeamService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {
    private TeamService teamService;
    private Mapper<TeamEntity, TeamDto> teamMapper;

    public TeamController(TeamService teamService, Mapper<TeamEntity, TeamDto> teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @PostMapping(path = "/teams")
    public ResponseEntity<TeamDto> createTeam(@RequestBody TeamDto team) {
        TeamEntity teamEntity = teamMapper.mapFrom(team);
        TeamEntity savedTeamEntity =  teamService.saveTeam(teamEntity);
        return new ResponseEntity<>(teamMapper.mapTo(savedTeamEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/teams")
    public ResponseEntity<List<TeamDto>> getAllTeams() {
        List<TeamEntity> teams = teamService.getAllTeams();
        return new ResponseEntity<>(teams.stream()
                .map(teamMapper::mapTo)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(path = "/teams/{id}")
    public ResponseEntity<TeamDto> getTeamById(@PathVariable("id") Long id) {
        Optional<TeamEntity> foundTeam = teamService.getTeamById(id);
        return foundTeam.map(teamEntity -> {
            TeamDto teamDto = teamMapper.mapTo(teamEntity);
            return new ResponseEntity<>(teamDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "teams/{id}")
    public ResponseEntity<TeamDto> fullUpdateTeam(@PathVariable("id") Long id, @RequestBody TeamDto teamDto) {
        if (!teamService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teamDto.setTeamId(id);
        TeamEntity teamEntity = teamMapper.mapFrom(teamDto);
        TeamEntity savedTeamEntity = teamService.saveTeam(teamEntity);
        return new ResponseEntity<>(teamMapper.mapTo(savedTeamEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "teams/{id}")
    public ResponseEntity<TeamDto> deleteTeamById(@PathVariable("id") Long id) {
        if (!teamService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        teamService.deleteTeamById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}