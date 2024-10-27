package com.cing.pizarra;

import com.cing.pizarra.domain.dto.PlayerDto;
import com.cing.pizarra.domain.dto.TeamDto;
import com.cing.pizarra.domain.entities.PlayerEntity;
import com.cing.pizarra.domain.entities.TeamEntity;

public final class TestDataUtil {
    private TestDataUtil() {}

    public static PlayerEntity createTestPlayerEntityA(final TeamEntity teamEntity) {
        return PlayerEntity.builder()
                .playerId(1L)
                .firstName("Cristiano")
                .lastName("Ronaldo")
                .preferredName("Ronaldo")
                .kitNumber(7)

                .active(false)
                .legend(true)

                .position("Forward")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(teamEntity)
                .build();
    }

    public static PlayerDto createTestPlayerDtoA() {
        return PlayerDto.builder()
                .playerId(1L)
                .firstName("Cristiano")
                .lastName("Ronaldo")
                .preferredName("Ronaldo")
                .kitNumber(7)

                .active(false)
                .legend(true)

                .position("Forward")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(null)
                .build();
    }

    public static PlayerEntity createTestPlayerEntityB() {
        return PlayerEntity.builder()
                .playerId(2L)
                .firstName("Sergio")
                .lastName("Ramos")
                .preferredName("Ramos")
                .kitNumber(4)

                .active(false)
                .legend(true)

                .position("Defender")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(null)
                .build();
    }

    public static PlayerDto createTestPlayerDtoB() {
        return PlayerDto.builder()
                .playerId(2L)
                .firstName("Sergio")
                .lastName("Ramos")
                .preferredName("Ramos")
                .kitNumber(4)

                .active(false)
                .legend(true)

                .position("Defender")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(null)
                .build();
    }

    public static PlayerEntity createTestPlayerEntityC() {
        return PlayerEntity.builder()
                .playerId(3L)
                .firstName("Kylian")
                .lastName("Mbappe")
                .preferredName("Mbappe")
                .kitNumber(9)

                .active(true)
                .legend(false)

                .position("Forward")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(null)
                .build();
    }

    public static PlayerDto createTestPlayerDtoC(final TeamDto teamDto) {
        return PlayerDto.builder()
                .playerId(3L)
                .firstName("Kylian")
                .lastName("Mbappe")
                .preferredName("Mbappe")
                .kitNumber(9)

                .active(true)
                .legend(false)

                .position("Forward")
                .image("img")

                .birthDate("birthDate")
                .joinDate("joinDate")
                .releaseDate("releaseDate")

                .team(teamDto)
                .build();
    }
}
