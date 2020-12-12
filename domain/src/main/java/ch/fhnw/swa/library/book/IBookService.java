package ch.fhnw.swa.library.book;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookService {
    int addBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(UUID id);
    int removeBookById(UUID id);
    int updateBookById(UUID id, Book book);
}
