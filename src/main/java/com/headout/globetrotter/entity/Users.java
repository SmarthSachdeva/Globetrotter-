package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private Integer score;

    @OneToMany(mappedBy = "user")
    private List<UserGuesses> userGuesses;
}