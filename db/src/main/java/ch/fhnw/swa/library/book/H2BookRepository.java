package ch.fhnw.swa.library.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

@AllArgsConstructor
public class H2BookRepository implements IBookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final IBookFactory bookFactory;

    @Override
    public boolean createBook(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books(id,title,author,description) VALUES (?,?,?,?);", // all ? are replaced, based on their position of parameter
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getDescription()
        );
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                "SELECT * FROM books;",
                (resultSet, rowNumber) -> createBookFromResponse(resultSet)
        );
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "SELECT * FROM books WHERE id = " + id + ";",
                (resultSet, rowNumber) -> createBookFromResponse(resultSet))
        );
    }

    @Override
    public boolean removeBookById(long id) {
        jdbcTemplate.execute(
                "DELETE FROM books WHERE id = " + id + ";"
        );
        return true;
    }

    @Override
    public boolean updateBook(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET title = ?,author = ?,description = ? WHERE id = ?;", // all ? are replaced, based on their position of parameter
                book.getTitle(),
                book.getAuthor(),
                book.getDescription(),
                book.getId()
        );
        return true;
    }

    private Book createBookFromResponse(ResultSet resultSet) throws SQLException {
        return bookFactory.createSpecificBook(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("description")
        );
    }
}
