package ch.fhnw.swa.library.book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.JdbcTemplate;

@AllArgsConstructor
public class H2BookRepository implements IBookRepository{
    private final JdbcTemplate jdbcTemplate;
    private final IBookFactory bookFactory;

    @Override
    public int createBook(Book book) {
        return 0;
    }

    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(
                "SELECT * FROM books",
                (rs, rowNum) -> constructBookFromResponse(rs));
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return Optional.empty();
    }

    @Override
    public int removeBookById(long id) {
        return 0;
    }

    @Override
    public int updateBook(Book book) {
        return 0;
    }

    private Book constructBookFromResponse(ResultSet resultSet) throws SQLException {
        return bookFactory.createSpecificBook(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getString("description")
        );
    }
}
