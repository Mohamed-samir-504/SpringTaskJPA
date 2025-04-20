package org.example.springtaskjpa.Services;

import org.example.springtaskjpa.Models.Author;
import org.example.springtaskjpa.Models.Course;
import org.example.springtaskjpa.Repositories.AuthorRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void findByEmail_shouldReturnCorrectAuthor() {
        Author mockAuthor = new Author(1,"mtolba","mtolba@sumerge.com");

        when(authorRepository.findByEmail(mockAuthor.getEmail())).thenReturn(Optional.of(mockAuthor));

        Optional<Author> result = authorService.getAuthorByEmail("mtolba@sumerge.com");

        assertEquals(1, result.get().getAuthor_id());
        assertEquals("mtolba", result.get().getName());
        assertEquals("mtolba@sumerge.com", result.get().getEmail());

    }
}

