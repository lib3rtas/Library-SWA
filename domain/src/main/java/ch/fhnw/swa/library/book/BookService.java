package ch.fhnw.swa.library.book;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class BookService implements IBookService {
    private final IBookRepository bookRepository;

    @Override
    public boolean createBook(Book book) {
        return bookRepository.createBook(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public boolean removeBookById(long id) {
        return bookRepository.removeBookById(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookRepository.updateBook(book);
    }
}
