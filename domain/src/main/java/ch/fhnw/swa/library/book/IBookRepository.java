package ch.fhnw.swa.library.book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookRepository {
    int addBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(UUID id);

    int removeBookById(UUID id);

    int updateBookById(UUID id, Book book);
}
