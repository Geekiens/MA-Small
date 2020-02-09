package hello.presentation;

import hello.business.BookService;
import hello.persistence.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BookController {
    //@RequestMapping(value = "/book")

    @Autowired
    BookService bookService;

    @GetMapping(path="/books/{id}", produces = "application/json")
    public Book book(@PathVariable("id") long id) {
        return bookService.getBook(id);
    }

    @GetMapping(path="/books/{id}", produces = "application/json")
    public Book book() {
        return bookService.getBooks();
    }

    @PostMapping(path= "/books", consumes = "application/json", produces = "application/json")
    public void createBook(@RequestBody Book book){
        System.out.println("Book:" + book.getAuthor());
    }


}
