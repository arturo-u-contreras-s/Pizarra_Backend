package com.cing.pizarra;

import com.cing.pizarra.domain.dto.PlayerDto;
import com.cing.pizarra.domain.dto.TeamDto;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.domain.entities.TeamEntity;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static TeamEntity createTestTeamEntityA() {
        return TeamEntity.builder()
                .teamId(1L)
                .teamName("Primer Equipo")
                .teamHeadCoach("Carlo Ancelotti")
                .build();
    }

    public static TeamDto createTestTeamDtoA() {
        return TeamDto.builder()
                .teamId(1L)
                .teamName("Primer Equipo")
                .teamHeadCoach("Carlo Ancelotti")
                .build();
    }

    public static TeamEntity createTestTeamEntityB() {
        return TeamEntity.builder()
                .teamId(2L)
                .teamName("Castilla")
                .teamHeadCoach("Raul Gonzalez")
                .build();
    }

    public static TeamEntity createTestTeamEntityC() {
        return TeamEntity.builder()
                .teamId(3L)
                .teamName("C")
                .teamHeadCoach("Pau Quesada")
                .build();
    }

    public static PlayerEntity createTestPlayerEntityA(final TeamEntity teamEntity) {
        return PlayerEntity.builder()
                .playerId(1L)
                .playerName("Cristiano Ronaldo")
                .kitNumber(7)
                .position("Forward")
                .team(teamEntity)
                .build();
    }

    public static PlayerDto createTestPlayerDtoA(final TeamDto teamDto) {
        return PlayerDto.builder()
                .playerId(1L)
                .playerName("Cristiano Ronaldo")
                .kitNumber(7)
                .position("Forward")
                .team(teamDto)
                .build();
    }

    public static PlayerEntity createTestPlayerB(final TeamEntity teamEntity) {
        return PlayerEntity.builder()
                .playerId(2L)
                .playerName("Sergio Ramos")
                .kitNumber(4)
                .position("Defender")
                .team(teamEntity)
                .build();
    }

    public static PlayerDto createTestPlayerDtoB(final TeamDto teamDto) {
        return PlayerDto.builder()
                .playerId(2L)
                .playerName("Sergio Ramos")
                .kitNumber(4)
                .position("Defender")
                .team(teamDto)
                .build();
    }

    public static PlayerEntity createTestPlayerC(final TeamEntity teamEntity) {
        return PlayerEntity.builder()
                .playerId(3L)
                .playerName("Luka Modric")
                .kitNumber(10)
                .position("Midfielder")
                .team(teamEntity)
                .build();
    }

    public static PlayerDto createTestPlayerDtoC(final TeamDto teamDto) {
        return PlayerDto.builder()
                .playerId(3L)
                .playerName("Luka Modric")
                .kitNumber(10)
                .position("Midfielder")
                .team(teamDto)
                .build();
    }
}
