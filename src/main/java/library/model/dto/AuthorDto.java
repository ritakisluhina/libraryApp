package library.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class AuthorDto {
    private Long id;
    private String name;
    private String lastName;
    private List<BookDto> booksList;
}
