package org.example.springtaskjpa.Services;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskjpa.Repositories.AuthorRepository;
import org.example.springtaskjpa.Models.Author;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorByEmail(String email){

        return Optional.ofNullable(authorRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Author with email " + email + " not found")));
    }

}
