package ch.fhnw.swa.library.book;

import java.util.List;

public interface IBookFactory {
    Book createSpecificBook(long id, String title, String author, String description);

    Book createRandomBook();

    List<Book> createListOfRandomBooks(int amount);
}
