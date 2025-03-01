package com.headout.globetrotter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Facts")
public class Facts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fact;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;
}
