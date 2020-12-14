package ch.fhnw.swa.library.book;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    int createBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(long id);

    int removeBookById(long id);

    int updateBook(Book book);
}
