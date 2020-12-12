package ch.fhnw.swa.library.web;

import ch.fhnw.swa.library.book.Book;
import ch.fhnw.swa.library.book.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@ComponentScan("ch.fhnw.swa.library.domain.book")
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    @GetMapping
    public String getAllBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return "/artifacts/list";
    }

    @GetMapping(path = "{id}")
    public String getBookById(Model model, @PathVariable UUID id) {
        Optional<Book> book = bookService.getBookById(id);
        model.addAttribute("book", book.get());
        return "books/show";
    }
}
