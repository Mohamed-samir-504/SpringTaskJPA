package org.example.springtaskjpa.Controllers;

import org.example.springtaskjpa.DTO.CourseDTO;
import org.example.springtaskjpa.Mappers.CourseMapper;
import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Services.CourseService;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CourseService courseService;

    @MockitoBean
    private CourseMapper courseMapper;

    @Test
    void viewCourse_shouldReturnCourseDTOByName() throws Exception {
        Course course = new Course(1L, "Spring", "Spring course");
        CourseDTO courseDTO = new CourseDTO("Spring","Spring course");

        when(courseService.getCourseByName("Spring")).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(courseDTO);

        mockMvc.perform(get("/view")
                        .param("name", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spring"))
                .andExpect(jsonPath("$.description").value("Spring course"));
    }

    @Test
    void viewAllCourses_shouldReturnAllCourses() throws Exception {
        List<Course> courseList = List.of(
                new Course(1L, "Java", "Java course"),
                new Course(2L, "Spring", "Spring course")
        );

        List<CourseDTO> courseDTOList = List.of(
                new CourseDTO( "Java", "Java course"),
                new CourseDTO("Spring", "Spring course")
        );

        when(courseService.getRecommendedCourses()).thenReturn(courseList);
        when(courseMapper.toDtoList(courseList)).thenReturn(courseDTOList);

        mockMvc.perform(get("/view/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Java"))
                .andExpect(jsonPath("$[0].description").value("Java course"))
                .andExpect(jsonPath("$[1].name").value("Spring"))
                .andExpect(jsonPath("$[1].description").value("Spring course"));

    }

    @Test
    void viewAllCourses_NoCoursesExist_shouldReturnNull() throws Exception {
        List<Course> courseList = List.of();

        List<CourseDTO> courseDTOList = List.of();


        when(courseService.getRecommendedCourses()).thenReturn(courseList);
        when(courseMapper.toDtoList(courseList)).thenReturn(courseDTOList);

        mockMvc.perform(get("/view/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }
}
