package org.example.springtaskjpa.CourseRecommenders;

import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Interfaces.CourseRecommender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("businessCourseRecommender")
public class BusinessCourseRecommender implements CourseRecommender {

    private List<Course> courses;

    @Override
    public List<Course> recommendedCourses() {
        this.courses = new ArrayList<Course>();
        courses.add(new Course("Business analysis","Sumerge business analysis"));
        courses.add(new Course("Business law","Sumerge business law"));
        courses.add(new Course("Business management", "Sumerge business management"));
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

//    public List<Course> getCourses() {
//        return courses;
//    }
}
