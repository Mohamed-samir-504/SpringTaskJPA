package org.example.springtaskjpa.Controller;

import org.example.springtaskjpa.DTO.CourseDTO;
import org.example.springtaskjpa.Mapper.CourseMapper;
import org.example.springtaskjpa.Model.Course;
import org.example.springtaskjpa.Service.CourseService;
import org.example.springtaskjpa.Service.ExternalRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    //It will use SoftwareCourseRecommender bean
    CourseService courseService;
    ExternalRatingService externalApiService;

    CourseMapper courseMapper;

    public CourseController(CourseService courseService, ExternalRatingService externalApiService
            ,CourseMapper courseMapper) {
        this.courseService = courseService;
        this.externalApiService = externalApiService;
        this.courseMapper = courseMapper;

    }

    //shows one course by its name
    //Using parameter request
    @GetMapping("/courses")
    public ResponseEntity<CourseDTO> viewCourse(@RequestParam String name) {

        return courseService.getCourseByName(name)
                .map(courseMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    //Shows all courses
    @GetMapping("/courses/all")
    public ResponseEntity<List<CourseDTO>> viewAllCourses() {

        List<Course> courses = courseService.getRecommendedCourses();
        return ResponseEntity.ok(courseMapper.toDtoList(courses));
    }


    //show paginated response
    @GetMapping("/courses/pages")
    public ResponseEntity<Page<Course>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.getCoursesPaginated(pageable);
        return ResponseEntity.ok(courses);
    }


    // Shows the form to add a course
    @GetMapping("/form")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }


    // Handles the form submission using model attribute
    @PostMapping("/new-course")
    public ResponseEntity<String> submitCourse(@ModelAttribute CourseDTO course) {
        courseService.addCourse(courseMapper.toEntity(course));
        return ResponseEntity.ok("Course added successfully.");
    }

    //using request body and path variable
    @PatchMapping("/courses/{id}")
    public ResponseEntity<String> updateCourse(@RequestBody Course course, @PathVariable Long id) {

        Optional<Course> originalCourse = courseService.getCourseById(id);
        courseService.updateCourse(originalCourse.get(),course);
        return ResponseEntity.ok("Course updated successfully.");
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully");
    }


    @GetMapping("/courses/rating")
    public ResponseEntity<String> getRating() {
        String json = externalApiService.getService();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(json);
    }




}
