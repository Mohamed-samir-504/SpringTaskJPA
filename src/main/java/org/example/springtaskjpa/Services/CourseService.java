package org.example.springtaskjpa.Services;


import org.example.springtaskjpa.Interfaces.CourseRepository;
import org.example.springtaskjpa.Models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public List<Course> getRecommendedCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByName(String name){
        return courseRepository.findFirstByName(name);
    }
    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public void addCourse (Course course) {
        courseRepository.save(course);
    }

    public void updateCourse (Course oldCourse,Course newCourse) {
        if(oldCourse != null && newCourse != null){
            if(newCourse.getName() != null) {
                oldCourse.setName(newCourse.getName());
            }
            if(newCourse.getDescription() != null) {
                oldCourse.setDescription(newCourse.getDescription());
            }
            courseRepository.save(oldCourse);
        }

    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }


}
