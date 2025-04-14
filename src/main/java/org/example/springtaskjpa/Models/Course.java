package org.example.springtaskjpa.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    private static int idCounter = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;


    @ManyToMany
    private List<Author> authors;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<>();

    @OneToOne
    private Assessment assessment;



    public Course() {
    }


    public Course(String name, String description) {
        this.id = idCounter++;
        this.name = name;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Course id: " + id + "\nCourse name: " + name +
                "\nCourse description: " + description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }


}
