package ch.fhnw.swa.library.book;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;

    @Override
    public int addBook(Book book) {
        return bookRepository.addBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Optional<Book> getBookById(UUID id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public int removeBookById(UUID id) {
        return bookRepository.removeBookById(id);
    }

    @Override
    public int updateBookById(UUID id, Book book) {
        return bookRepository.updateBookById(id, book);
    }
}
