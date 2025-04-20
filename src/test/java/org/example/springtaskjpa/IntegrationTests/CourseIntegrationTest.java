package org.example.springtaskjpa.IntegrationTests;

import org.example.springtaskjpa.Repositories.CourseRepository;
import org.example.springtaskjpa.Services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.example.springtaskjpa.Models.Course;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        Course course = new Course();
        course.setName("Testing");
        course.setDescription("Testing course");
        courseService.addCourse(course);
    }

    @Test
    void shouldReturnCourseDTO_whenCourseExistsByName() throws Exception {

        mockMvc.perform(get("/view")
                        .param("name", "Testing"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Testing"))
                .andExpect(jsonPath("$.description").value("Testing course"));
    }
}