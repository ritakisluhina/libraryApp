package library.dao;

import library.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    Author create(Author author);

    Author update(Author author);

    List<Author> getAll();

    Optional<Author> getById(Long id);

    List<Author> getByBookTitle(String title);
}
