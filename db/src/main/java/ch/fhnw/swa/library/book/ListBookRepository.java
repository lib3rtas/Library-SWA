package ch.fhnw.swa.library.book;

import java.util.*;

class ListBookRepository implements IBookRepository {

    private final List<Book> db;
    private final IBookFactory bookFactory;

    public ListBookRepository(IBookFactory bookFactory) {
        this.db = new ArrayList<>();
        this.bookFactory = bookFactory;
        fillListWithExampleData();
    }

    @Override
    public boolean createBook(Book book) {
        db.add(book);
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.copyOf(db);
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return db.stream()
                .filter(book -> book.getId() == (id))
                .findAny();
    }

    @Override
    public boolean removeBookById(long id) {
        Optional<Book> optionalBook = getBookById(id);
        if (optionalBook.isEmpty()) {
            return false;
        } else {
            db.remove(optionalBook.get());
            return true;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        Optional<Book> optionalArtifact = getBookById(book.getId());
        if (optionalArtifact.isEmpty()) {
            return false;
        } else {
            int index = db.indexOf(optionalArtifact.get());
            db.set(index, book);
            return true;
        }
    }

    // simple filler method, mainly for manual testing
    private void fillListWithExampleData() {
        String sampleDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in " +
                "voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                "proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Random rnd = new Random();
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Philosopher's Stone", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Chamber of Secrets", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Goblet of Fire", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Order of the Phoenix", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Half-Blood Prince", "J.K. Rowling", sampleDescription));
        db.add(bookFactory.createSpecificBook(rnd.nextLong(), "Harry Potter and the Deathly Hallows", "J.K. Rowling", sampleDescription));
    }
}
