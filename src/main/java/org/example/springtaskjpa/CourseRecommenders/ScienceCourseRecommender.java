package org.example.springtaskjpa.CourseRecommenders;

import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Interfaces.CourseRecommender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("scienceCourseRecommender")
public class ScienceCourseRecommender implements CourseRecommender {

    private List<Course> courses;

    @Override
    public List<Course> recommendedCourses() {
        this.courses = new ArrayList<Course>();
        courses.add(new Course("Physics", "Sumerge physics"));
        courses.add(new Course("Biology", "Sumerge biology"));
        courses.add(new Course("Chemistry", "Sumerge chemistry"));
        return courses;
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void updateCourse(Course course) {
        for(Course c : courses) {
            if(c.getId() == course.getId()) {
                c.setName(course.getName());
                c.setDescription(course.getDescription());
            }
        }
    }

    public void deleteCourse(int id) {
        for(Course c : courses) {
            if(c.getId() == id) {
                this.courses.remove(c);
            }
        }
    }


}
