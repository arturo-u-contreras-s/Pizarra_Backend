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
@Table(name="players")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_id_seq")
    private Long playerId;
    private String firstName;
    private String lastName;
    private String preferredName;
    private Integer kitNumber;

    private boolean active;
    private boolean legend;
    private boolean injured;

    private String position;
    private String image;

    private String birthDate;
    private String joinDate;
    private String releaseDate;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    private TeamEntity team;
}