package org.example.springtaskjpa.Interfaces;

import org.example.springtaskjpa.Models.Course;

import java.util.List;

public interface CourseRecommender {
    List<Course> recommendedCourses();
    public void addCourse(Course course);
    public void updateCourse(Course course);
    public void deleteCourse(int id);
}
