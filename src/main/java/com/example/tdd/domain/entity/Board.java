package com.example.tdd.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
