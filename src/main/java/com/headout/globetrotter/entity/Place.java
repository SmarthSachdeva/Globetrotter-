package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "Place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;
    private String name;

    @OneToMany(mappedBy = "place")
    private List<Clues> clues;

    @OneToMany(mappedBy = "place")
    private List<Facts> facts;

    @OneToMany(mappedBy = "place")
    private List<UserGuesses> userGuesses;

    @OneToMany(mappedBy = "guessedPlace")
    private List<UserGuesses> guessedPlaces;
}
