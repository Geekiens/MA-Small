package bookReviewer.presentation;

import bookReviewer.business.BookService;
import bookReviewer.business.OfferService;
import bookReviewer.business.model.Offer;
import bookReviewer.persistence.model.Book;
import bookReviewer.presentation.mapper.BookMapper;
import bookReviewer.presentation.model.BookPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    //@RequestMapping(value = "/book")

    @Autowired
    BookService bookService;

    @Autowired
    OfferService offerService;

    BookMapper bookMapper = new BookMapper();

    @GetMapping(path="/books/{id}", produces = "application/json")
    public BookPresentation getBook(@PathVariable("id") long id) {
        return bookMapper.map(bookService.getBook(id), offerService.getOffers(id));

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

    @DeleteMapping(path = "/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteBook(id);
    }


}
