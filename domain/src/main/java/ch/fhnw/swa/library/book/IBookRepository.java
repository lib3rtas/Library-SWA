package ch.fhnw.swa.library.book;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    boolean createBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(long id);

    boolean removeBookById(long id);

    boolean updateBook(Book book);
}
