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
@Table(name = "player-positions")
public class PlayerPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_pos_id_seq")
    private Long playerPositionId;
    private Long playerId;

    private Long squadPosition;
    @ManyToOne()
    @JoinColumn(name = "squadId")
    private SquadEntity squad;
}