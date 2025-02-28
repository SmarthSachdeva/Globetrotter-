package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "UserGuesses")
public class UserGuesses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "guessed_place_id")
    private Place guessedPlace;

    private Boolean isCorrect;
    private Integer score;
    private Timestamp guessedAt;

}