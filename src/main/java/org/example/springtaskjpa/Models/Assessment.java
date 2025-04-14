package org.example.springtaskjpa.Models;

import jakarta.persistence.*;


@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String content;

    @OneToOne
    private Course course;

}
