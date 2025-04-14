package org.example.springtaskjpa.Models;

import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;

    @ManyToOne
    @JoinColumn(name = "id")
    private Course course;
}
