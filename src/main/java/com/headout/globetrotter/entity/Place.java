package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alias;
    private String name;

    @OneToMany(mappedBy = "place")
    public List<Clues> clues;

    @OneToMany(mappedBy = "place")
    public List<Facts> facts;

    @OneToMany(mappedBy = "place")
    public List<UserGuesses> userGuesses;

    @OneToMany(mappedBy = "guessedPlace")
    public List<UserGuesses> guessedPlaces;

    public Place(Integer id) {
        this.id = id;
    }
}
