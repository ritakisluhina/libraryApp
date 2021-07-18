package library.service;

import library.model.Author;
import library.model.dto.AuthorDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {
    Author create(Author author);

    ResponseEntity<Object> update(Author author, Long id);

    List<AuthorDto> getAll();

    AuthorDto getById(Long id);

    List<AuthorDto> getByBookTitle(String title);
}
