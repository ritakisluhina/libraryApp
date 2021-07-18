package library.service.impl;

import library.dao.BookDao;
import library.model.Book;
import library.model.dto.BookDto;
import library.model.dto.mapper.BookMapper;
import library.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Book create(Book book) {
        return bookDao.create(book);
    }

    @Override
    public ResponseEntity<Object> update(Book book, Long id) {
        Optional<Book> bookOptional = bookDao.getById(id);
        if (!bookOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        book.setId(id);
        bookDao.update(book);
        return ResponseEntity.noContent().build();
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> bookOptional = bookDao.getById(id);
        if (!bookOptional.isPresent()) {
            return null;
        }
        Book book = bookOptional.get();
        BookDto bookDto = BookMapper.bookMapper.fromBook(book);
        return bookDto;
    }

    @Override
    public List<BookDto> getAll() {
        List<BookDto> bookDtos = new ArrayList<>();
        List<Book> bookList = bookDao.getAll();
        for (Book book : bookList) {
            bookDtos.add(BookMapper.bookMapper.fromBook(book));
        }
        return bookDtos;
    }
}
