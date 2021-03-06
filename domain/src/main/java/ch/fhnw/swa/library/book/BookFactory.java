package ch.fhnw.swa.library.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class BookFactory implements IBookFactory {
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    // create specific book instance
    @Override
    public Book createSpecificBook(long id, String title, String author, String description) {
        return new Book(id, title, author, description);
    }

    // create random book
    @Override
    public Book createRandomBook() {
        return new Book(
                new Random().nextLong(),
                generateRandomString(10),
                generateRandomString(8),
                generateRandomString(100)

        );
    }

    // create list of random books
    @Override
    public List<Book> createListOfRandomBooks(int amount) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            books.add(createRandomBook());
        }
        return books;
    }

    // https://stackoverflow.com/a/157202
    private static String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int c = 0; c < length; c++) {
            stringBuilder.append(random.nextInt(characters.length()));
        }
        return stringBuilder.toString();
    }
}
