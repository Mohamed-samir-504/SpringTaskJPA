package org.example.springtaskjpa.Interfaces;

import org.example.springtaskjpa.Models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    public Optional<Course> findFirstByName(String name);
}
