package com.cing.pizarra.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "squads")
public class SquadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "squad_id_seq")
    private Long squadId;
    private String squadName;
    private String lastUpDate;
    private String formation;
}