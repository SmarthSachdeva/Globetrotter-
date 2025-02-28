package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Clues")
public class Clues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clue;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

}