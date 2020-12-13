package ch.fhnw.swa.library.book;

import java.util.*;

class ListBookRepository implements IBookRepository {

    private final List<Book> db;

    public ListBookRepository(){
        db = new ArrayList<>();
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Philosopher's Stone"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Chamber of Secrets"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Prisoner of Azkaban"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Goblet of Fire"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Order of the Phoenix"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Half-Blood Prince"));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Deathly Hallows"));
    }

    @Override
    public int createBook(Book book) {
        db.add(book);
        return 1;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.copyOf(db);
    }

    @Override
    public Optional<Book> getBookById(UUID id) {
        return db.stream()
                .filter(book -> book.getId().equals(id))
                .findAny();
    }

    @Override
    public int removeBookById(UUID id) {
        Optional<Book> optionalBook = getBookById(id);
        if (optionalBook.isEmpty()){
            return 0;
        } else {
            db.remove(optionalBook.get());
            return 1;
        }
    }

    @Override
    public int updateBook(Book book) {
        Optional<Book> optionalArtifact = getBookById(book.getId());
        if (optionalArtifact.isEmpty()){
            return 0;
        } else {
            int index = db.indexOf(optionalArtifact.get());
            db.set(index, book);
            return 1;
        }
    }
}
