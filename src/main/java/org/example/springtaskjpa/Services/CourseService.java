package org.example.springtaskjpa.Services;


import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskjpa.Exceptions.GlobalExceptionHandler;
import org.example.springtaskjpa.Repositories.CourseRepository;
import org.example.springtaskjpa.Models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;


    public Page<Course> getCoursesPaginated(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
    public List<Course> getRecommendedCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByName(String name){
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Course name must not be blank");
        }
        return Optional.ofNullable(courseRepository.findFirstByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Course with name " + name + " not found")));

    }
    public Optional<Course> getCourseById(Long id){
        if (id == null) {
            throw new IllegalArgumentException("Course id must not be blank");
        }
        return Optional.ofNullable(courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + id + " not found")));

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
