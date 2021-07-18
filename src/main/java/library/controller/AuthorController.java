package library.controller;

import library.model.Author;
import library.model.dto.AuthorDto;
import library.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @GetMapping("/getByKeyword")
    public List<AuthorDto> getByBookKeyword(@RequestParam String title) {
        return authorService.getByBookTitle(title);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAuthor(@RequestBody Author author, @PathVariable Long id) {
        return authorService.update(author, id);
    }

    @PostMapping
    public Author addAuthor(@RequestBody Author author) {
        return authorService.create(author);
    }
}
