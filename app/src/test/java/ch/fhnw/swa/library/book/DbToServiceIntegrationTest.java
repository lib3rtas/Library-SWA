package ch.fhnw.swa.library.book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Integration Test from DB Layer up to Service Layer
 */
@SpringBootTest
class DbToServiceIntegrationTest {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IBookFactory bookFactory;

    // Sanity Check to see if service is loaded by context
    @Test
    void contextLoads() throws Exception {
        assertThat(bookService).isNotNull();
    }

    @Test
    void createANewBookAndFindItByIdTest() {
        // Arrange
        // Get the list of books before Book is added
        int nrOfBooksBefore = bookService.getAllBooks().size();

        // Act
        // Create a new Book and add it
        Book bookToBeAdded = new Book((long) 123, "Title", "Author", "Description");
        bookService.createBook(bookToBeAdded);
        int nrOfBooksAfter = bookService.getAllBooks().size();

        // Assert
        // check if exactly one book was added
        assertThat(nrOfBooksBefore + 1).isEqualTo(nrOfBooksAfter);

        // check if new book is correctly stored in list
        Optional<Book> optionalBook = bookService.getBookById(bookToBeAdded.getId());
        assertThat(optionalBook).isNotNull();
        assertThat(optionalBook.isPresent()).isTrue();
        Book addedBook = optionalBook.get();
        assertThat(addedBook).isNotNull();


        // check that no properties are not null
        assertThat(addedBook.getId()).isNotNull();
        assertThat(addedBook.getTitle()).isNotNull();
        assertThat(addedBook.getAuthor()).isNotNull();
        assertThat(addedBook.getDescription()).isNotNull();

        // check that both books properties are actually equal
        assertThat(addedBook.getId()).isEqualTo(bookToBeAdded.getId());
        assertThat(addedBook.getTitle()).isEqualTo(bookToBeAdded.getTitle());
        assertThat(addedBook.getAuthor()).isEqualTo(bookToBeAdded.getAuthor());
        assertThat(addedBook.getDescription()).isEqualTo(bookToBeAdded.getDescription());
    }

    @Test
    void createANewBookAndUpdateOnlyTitleProperty() {
        // Arrange
        // Get the nr of books before Book is added
        int nrOfBooksBefore = bookService.getAllBooks().size();

        // Act
        // Create a new Book and add it
        Book bookToBeAdded = new Book((long) 456, "Title2", "Author2", "Description2");
        bookService.createBook(bookToBeAdded);

        Book passedUpdatedBook = new Book(bookToBeAdded.getId(), "Title2New", "Author2", "Description2");
        bookService.updateBook(passedUpdatedBook);
        int nrOfBooksAfter = bookService.getAllBooks().size();

        // Assert
        // check if exactly one book was added
        assertThat(nrOfBooksBefore + 1).isEqualTo(nrOfBooksAfter);

        // check if new book is correctly stored in list
        Optional<Book> optionalBook = bookService.getBookById(bookToBeAdded.getId());
        assertThat(optionalBook).isNotNull();
        assertThat(optionalBook.isPresent()).isTrue();
        Book updatedBook = optionalBook.get();
        assertThat(updatedBook).isNotNull();

        // check that no properties are not null
        assertThat(updatedBook.getId()).isNotNull();
        assertThat(updatedBook.getTitle()).isNotNull();
        assertThat(updatedBook.getAuthor()).isNotNull();
        assertThat(updatedBook.getDescription()).isNotNull();

        // check if the book properties were updated
        assertThat(updatedBook.getId()).isEqualTo(bookToBeAdded.getId());
        assertThat(updatedBook.getTitle()).isEqualTo(passedUpdatedBook.getTitle());
        assertThat(updatedBook.getAuthor()).isEqualTo(bookToBeAdded.getAuthor());
        assertThat(updatedBook.getDescription()).isEqualTo(bookToBeAdded.getDescription());
    }

    @Test
    void createANewBookAndUpdateAllProperties() {
        // Arrange
        // Get the nr of books before Book is added
        int nrOfBooksBefore = bookService.getAllBooks().size();

        // Act
        // Create a new Book and add it
        Book bookToBeAdded = new Book((long) 789, "Title3", "Author3", "Description3");
        bookService.createBook(bookToBeAdded);

        Book passedUpdatedBook = new Book(bookToBeAdded.getId(), "Title3New", "Author3New", "Description3New");
        bookService.updateBook(passedUpdatedBook);
        int nrOfBooksAfter = bookService.getAllBooks().size();

        // Assert
        // check if exactly one book was added
        assertThat(nrOfBooksBefore + 1).isEqualTo(nrOfBooksAfter);

        // check if new book is correctly stored in list
        Optional<Book> optionalBook = bookService.getBookById(bookToBeAdded.getId());
        assertThat(optionalBook).isNotNull();
        assertThat(optionalBook.isPresent()).isTrue();
        Book updatedBook = optionalBook.get();
        assertThat(updatedBook).isNotNull();

        // check that no properties are not null
        assertThat(updatedBook.getId()).isNotNull();
        assertThat(updatedBook.getTitle()).isNotNull();
        assertThat(updatedBook.getAuthor()).isNotNull();
        assertThat(updatedBook.getDescription()).isNotNull();

        // check if the book properties were updated
        assertThat(updatedBook.getId()).isEqualTo(bookToBeAdded.getId());
        assertThat(updatedBook.getTitle()).isEqualTo(passedUpdatedBook.getTitle());
        assertThat(updatedBook.getAuthor()).isEqualTo(passedUpdatedBook.getAuthor());
        assertThat(updatedBook.getDescription()).isEqualTo(passedUpdatedBook.getDescription());
    }

    @Test
    void createSomeBooksWithFactoryAndThenDeleteSome() {
        // Arrange
        // Get the nr of books before Book is added
        int nrOfBooksBefore = bookService.getAllBooks().size();

        // Act
        // Create a load of new Books and add them
        Book book1 = bookFactory.createSpecificBook((long) 111, "Title1", "Author1", "Description1");
        Book book2 = bookFactory.createSpecificBook((long) 222, "Title2", "Author2", "Description2");
        Book book3 = bookFactory.createSpecificBook((long) 333, "Title3", "Author3", "Description3");
        Book book4 = bookFactory.createSpecificBook((long) 444, "Title4", "Author4", "Description4");

        bookService.createBook(book1);
        bookService.createBook(book2);
        bookService.createBook(book3);
        bookService.createBook(book4);
        int nrOfBooksAfterCreate = bookService.getAllBooks().size();

        // check if the correct number of books was added
        assertThat(nrOfBooksBefore + 4).isEqualTo(nrOfBooksAfterCreate);

        bookService.removeBookById(book1.getId());
        bookService.removeBookById(book4.getId());
        bookService.removeBookById(book2.getId());
        int nrOfBooksAfterRemove = bookService.getAllBooks().size();

        assertThat(nrOfBooksBefore + 1).isEqualTo(nrOfBooksAfterRemove);

        // check if new book is correctly stored in list
        Optional<Book> optionalBook = bookService.getBookById(book3.getId());
        assertThat(optionalBook).isNotNull();
        assertThat(optionalBook.isPresent()).isTrue();
        Book lastRemainingBook = optionalBook.get();
        assertThat(lastRemainingBook).isNotNull();

        // check that no properties are not null
        assertThat(lastRemainingBook.getId()).isNotNull();
        assertThat(lastRemainingBook.getTitle()).isNotNull();
        assertThat(lastRemainingBook.getAuthor()).isNotNull();
        assertThat(lastRemainingBook.getDescription()).isNotNull();

        // check if the book properties were updated
        assertThat(lastRemainingBook.getId()).isEqualTo(book3.getId());
        assertThat(lastRemainingBook.getTitle()).isEqualTo(book3.getTitle());
        assertThat(lastRemainingBook.getAuthor()).isEqualTo(book3.getAuthor());
        assertThat(lastRemainingBook.getDescription()).isEqualTo(book3.getDescription());
    }
}
