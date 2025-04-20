package org.example.springtaskjpa.Controllers;

import org.example.springtaskjpa.DTO.CourseDTO;
import org.example.springtaskjpa.Mappers.CourseMapper;
import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Services.CourseService;
import org.example.springtaskjpa.Services.ExternalApiService;
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

    ExternalApiService externalApiService;

    @Autowired
    CourseMapper courseMapper;

    public CourseController(CourseService courseService, ExternalApiService externalApiService) {
        this.courseService = courseService;
        this.externalApiService = externalApiService;

    }

    //shows one course by its name
    //Using parameter request
    @GetMapping("/view")
    public ResponseEntity<CourseDTO> viewCourse(@RequestParam String name) {

        return courseService.getCourseByName(name)
                .map(courseMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    //Shows all courses
    @GetMapping("/view/all")
    public ResponseEntity<List<CourseDTO>> viewAllCourses() {
        List<Course> courses = courseService.getRecommendedCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.ok().body(null);
        }

        for (Course course : courses) {
            System.out.println(course.getName());
            System.out.println(course.getDescription());
        }

        return ResponseEntity.ok(courseMapper.toDtoList(courses));

    }


    //show paginated response
    @GetMapping("/view/pages")
    public ResponseEntity<Page<Course>> getCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.getCoursesPaginated(pageable);
        return ResponseEntity.ok(courses);
    }


    // Shows the form to add a course
    @GetMapping("/add")
    public RedirectView showAddForm() {
        return new RedirectView("/Add.html");
    }


    // Handles the form submission using model attribute
    @PostMapping("/add-submit")
    public ResponseEntity<String> submitCourse(@ModelAttribute CourseDTO course) {
        //System.out.println(course);
        courseService.addCourse(courseMapper.toEntity(course));
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


    @GetMapping("/courses/{id}/service")
    public ResponseEntity<String> getRating(@PathVariable Long id) {
        String json = externalApiService.getService(id); // returns raw JSON string
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON) // ✅ add this!
                .body(json);
    }




}
