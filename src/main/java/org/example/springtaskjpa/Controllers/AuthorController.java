package org.example.springtaskjpa.Controllers;

import org.example.springtaskjpa.Interfaces.AuthorRepository;
import org.example.springtaskjpa.Models.Author;
import org.example.springtaskjpa.Services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthorController {
    AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ResponseEntity<Author> findByEmail(@RequestParam String email){
        Author author = authorService.findByEmail(email);

        return ResponseEntity.ok(author);

    }
}
