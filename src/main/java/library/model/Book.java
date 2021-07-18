package library.model;

import lombok.Data;

import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToMany
    @JoinTable (name="authors_books",
            joinColumns=@JoinColumn (name="book_id"),
            inverseJoinColumns=@JoinColumn(name="author_id"))
    private List<Author> authors;
}
