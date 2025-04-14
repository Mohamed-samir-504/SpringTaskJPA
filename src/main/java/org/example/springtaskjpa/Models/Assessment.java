package org.example.springtaskjpa.Models;

import jakarta.persistence.*;


@Entity
public class Assessment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int assessment_id;

    private String content;


}
