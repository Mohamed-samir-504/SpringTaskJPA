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

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        Course course = new Course(1L, "Spring", "Spring Boot course");
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Spring");
        courseDTO.setDescription("Spring Boot course");


        when(courseService.getCourseByName("Spring")).thenReturn(Optional.of(course));
        when(courseMapper.toDto(course)).thenReturn(courseDTO);

        mockMvc.perform(get("/view")
                        .param("name", "Spring"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Spring"))
                .andExpect(jsonPath("$.description").value("Spring Boot course"));
    }
}
