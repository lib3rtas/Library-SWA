package ch.fhnw.swa.library.web.book;

import ch.fhnw.swa.library.book.BookService;
import ch.fhnw.swa.library.book.IBookRepository;
import ch.fhnw.swa.library.book.IBookService;
import ch.fhnw.swa.library.book.ListBookRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookWebConfiguration {
	@Bean
    IBookService bookService(IBookRepository bookRepository){
		return new BookService(bookRepository);
	}
	@Bean
	IBookRepository bookRepository(){
		return new ListBookRepository();
	}
}
