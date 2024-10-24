package com.cing.pizarra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayerPositionDto {
    private Long playerPositionId;
    private Long playerId;

    private Long squadPosition;
    private SquadDto squad;
}