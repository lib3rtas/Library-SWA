package ch.fhnw.swa.library.web;

import ch.fhnw.swa.library.book.Book;
import ch.fhnw.swa.library.book.IBookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@AllArgsConstructor
@ComponentScan("ch.fhnw.swa.library.book")
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    @PostMapping("create")
    public String createBook(Book book) {
        bookService.createBook(book);
        return "redirect:/books";
    }

    @PostMapping("update")
    public String updateBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/books";
    }

    @GetMapping
    public String getBookListForm(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "/books/list";
    }

    @GetMapping(path = "{id}")
    public String getBookForm(Model model, @PathVariable UUID id) {
        Optional<Book> book = bookService.getBookById(id);
        model.addAttribute("book", book.get());
        return "/books/show";
    }

    @GetMapping(path = "{id}/update")
    public String getUpdateBookForm(Model model, @PathVariable UUID id) {
        Optional<Book> book = bookService.getBookById(id);
        model.addAttribute("book", book.get());
        return "/books/update";
    }

    @GetMapping(path = "create")
    public String getCreateBookForm(Model model){
        model.addAttribute("book", new Book());
        return "/books/create";
    }

    @GetMapping(path = "{id}/delete")
    public String deleteBookRelay(@PathVariable UUID id) {
        Optional<Book> book = bookService.getBookById(id);
        bookService.removeBookById(book.get().getId());
        return "redirect:/books";
    }
}
