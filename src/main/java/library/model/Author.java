package library.model;

import lombok.Data;

import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @ManyToMany
    @JoinTable (name="authors_books",
            joinColumns=@JoinColumn (name="author_id"),
            inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> books;
}
