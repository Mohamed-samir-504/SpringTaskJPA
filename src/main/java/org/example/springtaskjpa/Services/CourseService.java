package org.example.springtaskjpa.Services;


import org.example.springtaskjpa.Interfaces.CourseRecommender;
import org.example.springtaskjpa.Interfaces.CourseRepository;
import org.example.springtaskjpa.Models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRecommender courseRecommender;

    @Autowired
    private CourseRepository courseRepository;

    //Using variable name
    //remove primary annotation form softwareCourseRecommender bean first
    //@Autowired
    //private CourseRecommender scienceCourseRecommender;


    //Constructor using qualifier on businessCourseRecommender bean
    //Called by default
    public CourseService( CourseRecommender courseRecommender) {

        this.courseRecommender = courseRecommender;
    }

    //Setter using primary annotation on softwareCourseRecommender bean
    //@Autowired
//    public void setCourseRecommender(CourseRecommender courseRecommender) {
//        System.out.println("Setter");
//        this.courseRecommender = courseRecommender;
//    }

    public List<Course> getRecommendedCourses(){
        return courseRecommender.recommendedCourses();
    }


    public void addCourse (Course course) {
        courseRecommender.addCourse(course);
    }

    public void updateCourse (Course course) {
        courseRecommender.updateCourse(course);
    }

    public void deleteCourse(int id) {
        courseRecommender.deleteCourse(id);
    }

    //    List<Course> getScienceRecommendedCourses(){
//        return scienceCourseRecommender.recommendedCourses();
//    }

}
