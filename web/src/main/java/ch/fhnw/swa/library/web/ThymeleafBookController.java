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

/**
 * Book Server Page Controller Implementation
 */
@Controller
@AllArgsConstructor
@ComponentScan("ch.fhnw.swa.library.book")
public class ThymeleafBookController {
    private final IBookService bookService;


    // List all Books
    @GetMapping("/books")
    public String getPageBookList(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }


    // Show specific Book
    @GetMapping(path = "/books/{id}")
    public String getPageBookShow(Model model, @PathVariable long id) {
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            // Book exists, display it
            model.addAttribute("book", book.get());
            return "books/show";
        } else {
            // Book not found, display error page
            return "redirect:/error";
        }
    }


    // Create Book
    @PostMapping("/books/create")
    public String createBook(Book book) {
        if (bookService.createBook(book)) {
            return "redirect:/books";
        } else {
            // Book could not be created, display error page
            return "redirect:/error";
        }

    }

    @GetMapping(path = "/books/create")
    public String getPageBookCreate(Model model) {
        model.addAttribute("book", new Book());
        return "books/create";
    }


    // Update Book
    @PostMapping("/books/update")
    public String updateBook(Book book) {
        if (bookService.updateBook(book)) {
            return "redirect:/books";
        } else {
            // Book could not be updated, display error page
            return "redirect:/error";
        }
    }

    @GetMapping(path = "/books/{id}/update")
    public String getPageUpdateBook(Model model, @PathVariable long id) {
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            // Book exists, update it
            model.addAttribute("book", book.get());
            return "books/update";
        } else {
            // Book not found, display error page
            return "redirect:/error";
        }
    }


    // Delete Book
    // Http Method is GET because way less complex this way with thymeleaf
    @GetMapping(path = "/books/{id}/delete")
    public String deleteBook(Model model, @PathVariable long id) {
        Optional<Book> book = bookService.getBookById(id);

        if (book.isPresent()) {
            // Book exists, delete it
            bookService.removeBookById(book.get().getId());
            return "redirect:/books";
        } else {
            // Book not found, display error page
            return "redirect:/error";
        }
    }
}
