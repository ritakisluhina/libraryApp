package library.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookDto {
    private Long id;
    private String title;
    private Long authorId;
    private List<AuthorDto> authorsList;
}
