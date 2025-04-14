package org.example.springtaskjpa.Controllers;

import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class CourseController {

    //It will use SoftwareCourseRecommender bean
    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //shows one course by its name
    //Using parameter request
    @GetMapping("/view")
    public ResponseEntity<String> viewCourse(@RequestParam String courseName) {
        List<Course> courses = courseService.getRecommendedCourses();

        for (Course course : courses) {
            if (course.getName().equals(courseName)) {
                return ResponseEntity.ok().body(course.toString());
            }
        }
        return ResponseEntity.notFound().build();
    }

    //Shows all courses
    @GetMapping("/view/all")
    public ResponseEntity<String> viewAllCourses() {
        List<Course> courses = courseService.getRecommendedCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.ok("No courses found.");
        }

        StringBuilder response = new StringBuilder();
        for (Course course : courses) {
            response.append(course.toString()).append("\n");
        }

        return ResponseEntity.ok().body(response + "\n");

    }

    // Shows the form to add a course
    @GetMapping("/add")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }

    // Handles the form submission using model attribute
    @PostMapping("/add-submit")
    public ResponseEntity<String> submitCourse(@ModelAttribute Course course) {

        courseService.addCourse(course);
        return ResponseEntity.ok("Course added successfully.");
    }

    //using request body and path variable
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable int id) {
        course.setId(id);
        courseService.updateCourse(course);
        return ResponseEntity.ok().body(course.toString());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }


}
