package ch.fhnw.swa.library.book;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Configuration for the ch.fhnw.swa.library.book domain
 */
@Configuration
public class BookConfiguration {
    @Bean
    IBookService bookService(@Qualifier("h2") IBookRepository bookRepository) {
        return new BookService(bookRepository);
    }

    @Bean("h2")
    IBookRepository h2BookRepository(JdbcTemplate jdbcTemplate, IBookFactory bookFactory) {
        return new H2BookRepository(jdbcTemplate, bookFactory);
    }

    @Bean("list")
    IBookRepository listBookRepository(IBookFactory bookFactory) {
        return new ListBookRepository(bookFactory);
    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean
    DataSource h2StoreInFileDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hibernate.dialect.H2Dialect");
        dataSource.setUrl("jdbc:h2:file:~/data");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    DataSource h2StoreInMemoryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hibernate.dialect.H2Dialect");
        dataSource.setUrl("jdbc:h2:mem:data");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

    @Bean
    IBookFactory bookFactory() {
        return new BookFactory();
    }
}
