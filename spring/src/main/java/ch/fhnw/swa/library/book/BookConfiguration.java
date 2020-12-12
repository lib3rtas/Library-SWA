package ch.fhnw.swa.library.book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookConfiguration {
	@Bean
	IBookService bookService(IBookRepository bookRepository){
		return new BookService(bookRepository);
	}
	@Bean
	IBookRepository bookRepository(){
		return new ListBookRepository();
	}
}
