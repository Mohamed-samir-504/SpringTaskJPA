package org.example.springtaskjpa.UnitTests.Controllers;

import org.example.springtaskjpa.Controllers.AuthorController;
import org.example.springtaskjpa.Models.Author;
import org.example.springtaskjpa.Services.AuthorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthorService authorService;

    @Test
    void findByEmail_shouldReturnCorrectAuthor() throws Exception {
        Author author = new Author(1,"mtolba","mtolba@sumerge.com");

        when(authorService.getAuthorByEmail("mtolba@sumerge.com")).thenReturn(Optional.of(author));

        mockMvc.perform(get("/author")
                        .param("email", author.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author_id").value(1))
                .andExpect(jsonPath("$.name").value("mtolba"))
                .andExpect(jsonPath("$.email").value("mtolba@sumerge.com"));
    }
}
