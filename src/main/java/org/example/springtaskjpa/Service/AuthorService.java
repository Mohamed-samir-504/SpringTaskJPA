package org.example.springtaskjpa.Service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springtaskjpa.Repository.AuthorRepository;
import org.example.springtaskjpa.Model.Author;
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
