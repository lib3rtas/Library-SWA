package ch.fhnw.swa.library.book;

import java.util.*;

class ListBookRepository implements IBookRepository {

    private final List<Book> db;

    public ListBookRepository(){
        db = new ArrayList<>();
        fillListWithExampleData();
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

    private void fillListWithExampleData(){
        String sampleDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Philosopher's Stone",  sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Chamber of Secrets",   sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Prisoner of Azkaban",  sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Goblet of Fire",       sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Order of the Phoenix", sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Half-Blood Prince",    sampleDescription));
        db.add(new Book(UUID.randomUUID(), "Harry Potter and the Deathly Hallows",      sampleDescription));
    }
}
