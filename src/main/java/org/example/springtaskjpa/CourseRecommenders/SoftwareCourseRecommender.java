package org.example.springtaskjpa.CourseRecommenders;

import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Interfaces.CourseRecommender;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("softwareCourseRecommender")
@Primary
public class SoftwareCourseRecommender implements CourseRecommender {

    private List<Course> courses;

    public SoftwareCourseRecommender() {
        this.courses = new ArrayList<Course>();
//        courses.add(new Course("Java", "Sumerge Java"));
//        courses.add(new Course("Spring", "Sumerge Spring"));
//        courses.add(new Course("Maven", "Sumerge Maven"));
    }

    @Override
    public List<Course> recommendedCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        course.setId(this.courses.size() + 1);
        this.courses.add(course);

    }

    public void updateCourse(Course course) {
        for(Course c : courses) {
            if(c.getId() == course.getId()) {

                if(course.getName() != null && !course.getName().isEmpty()){
                    c.setName(course.getName());
                }
                if(course.getDescription() != null && !course.getDescription().isEmpty()){
                    c.setDescription(course.getDescription());
                }

            }
        }
    }

    public void deleteCourse(int id) {
        for(Course c : courses) {
            if(c.getId() == id) {
                this.courses.remove(c);
                return;
            }
        }
    }
}
