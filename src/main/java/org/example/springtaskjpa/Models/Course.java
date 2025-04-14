package org.example.springtaskjpa.Models;


public class Course {

    private static int idCounter = 1;

    private int id;
    private String name;
    private String description;


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
