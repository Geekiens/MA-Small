package hello.presentation;

import hello.business.BookService;
import hello.persistence.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BookController {
    //@RequestMapping(value = "/book")

    @Autowired
    BookService bookService;

    @GetMapping(path="/books/{id}", produces = "application/json")
    public Book getBook(@PathVariable("id") long id) {
        return bookService.getBook(id);
    }

    @GetMapping(path="/books", produces = "application/json")
    public List<Book> listBooks() {
        return bookService.getBooks();
    }

    @PostMapping(path= "/books", consumes = "application/json", produces = "application/json")
    public void createBook(@RequestBody Book book){
        bookService.createBook(book);
        System.out.println("Book:" + book.getAuthor());
    }


}
