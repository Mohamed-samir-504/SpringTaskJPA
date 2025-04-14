package org.example.springtaskjpa.Controllers;

import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Course> viewCourse(@RequestParam String name) {

        return courseService.getCourseByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    //Shows all courses
    @GetMapping("/view/all")
    public ResponseEntity<List<Course>> viewAllCourses() {
        List<Course> courses = courseService.getRecommendedCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.ok().body(null);
        }

        return ResponseEntity.ok(courses);

    }

    // Shows the form to add a course
    @GetMapping("/add")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }

    // Handles the form submission using model attribute
    @PostMapping("/add-submit")
    public ResponseEntity<String> submitCourse(@ModelAttribute Course course) {
        System.out.println(course);
        courseService.addCourse(course);
        return ResponseEntity.ok("Course added successfully.");
    }

    //using request body and path variable
    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long id) {
        Optional<Course> originalCourse = courseService.getCourseById(id);

        if (originalCourse.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        courseService.updateCourse(originalCourse.get(),course);
        return ResponseEntity.ok("Course updated successfully.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }


}
