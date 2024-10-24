package com.cing.pizarra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SquadDto {
    private Long squadId;
    private String squadName;
    private String lastUpDate;
    private String formation;
}
