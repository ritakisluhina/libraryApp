package library.service.impl;

import library.dao.AuthorDao;
import library.model.Author;
import library.model.dto.AuthorDto;
import library.model.dto.mapper.AuthorMapper;
import library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author create(Author author) {
        return authorDao.create(author);
    }

    @Override
    public ResponseEntity<Object> update(Author author, Long id) {
        Optional<Author> authorOptional = authorDao.getById(id);
        if (!authorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        author.setId(id);
        authorDao.update(author);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<AuthorDto> getAll() {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        List<Author> authorList = authorDao.getAll();
        for (Author author : authorList) {
            authorDtoList.add(AuthorMapper.authorMapper.fromAuthor(author));
        }
        return authorDtoList;
    }

    @Override
    public AuthorDto getById(Long id) {
        Optional<Author> optionalAuthor = authorDao.getById(id);
        if (!optionalAuthor.isPresent()) {
            return null;
        }
        Author author = optionalAuthor.get();
        AuthorDto authorDto = AuthorMapper.authorMapper.fromAuthor(author);
        return authorDto;
    }

    @Override
    public List<AuthorDto> getByBookTitle(String title) {
        List<AuthorDto> authorDtoList = new ArrayList<>();
        List<Author> authorList = authorDao.getByBookTitle(title);
        for (Author author : authorList) {
            authorDtoList.add(AuthorMapper.authorMapper.fromAuthor(author));
        }
        return authorDtoList;
    }
}
