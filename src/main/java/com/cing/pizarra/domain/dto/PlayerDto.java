package com.cing.pizarra.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters, setters, equals, hashcode, etc..
@AllArgsConstructor
@NoArgsConstructor // needed for Jackson
@Builder
public class PlayerDto {
    private Long playerId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private Integer kitNumber;

    private boolean active;
    private boolean legend;
    private boolean injured;

    private String position;
    private TeamDto team;
    private String image;

    private String birthDate;
    private String joinDate;
    private String releaseDate;
}