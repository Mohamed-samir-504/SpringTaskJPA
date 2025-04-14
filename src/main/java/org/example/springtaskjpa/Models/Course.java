package org.example.springtaskjpa.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {

    //private static int idCounter = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String description;


    //Join table is used to declare course as owner and to specify column names
    @ManyToMany
    @JoinTable(
            name = "course_authors",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors;

    //So there is no extra table created between course and rating
    @OneToMany(mappedBy = "course")
    private List<Rating> ratings;


    //join column is used to specify column name of foreign key mapped to pk of assessment
    @OneToOne
    @JoinColumn(name = "assessment_id", referencedColumnName = "assessment_id")
    private Assessment assessment;



    public Course() {
    }


    public Course(String name, String description) {
        //this.id = idCounter++;
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
