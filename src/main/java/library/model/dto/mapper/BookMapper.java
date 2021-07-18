package library.model.dto.mapper;

import library.model.Book;
import library.model.dto.BookDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorMapper.class)
public interface BookMapper {
    BookMapper bookMapper = Mappers.getMapper(BookMapper.class);
    @Mapping(source = "books", target = "booksList")
    BookDto fromBook(Book book);
}
