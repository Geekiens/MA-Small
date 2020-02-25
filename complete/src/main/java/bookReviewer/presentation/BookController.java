package bookReviewer.presentation;

import bookReviewer.business.BookService;
import bookReviewer.business.OfferService;
import bookReviewer.business.RatingService;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.Book;
import bookReviewer.presentation.mapper.BookMapper;
import bookReviewer.presentation.model.BookDetailPresentation;
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

    @Autowired
    RatingService ratingService;

    BookMapper bookMapper = new BookMapper();

    @GetMapping(path="/books/{id}", produces = "application/json")
    public BookDetailPresentation getBook(@PathVariable("id") long id) {
        BookDetailPresentation bookDetailPresentation = bookMapper.map(bookService.getBook(id), offerService.getOffers(id));
        RatingSummary ratingSummary = ratingService.getAverageRating(id);
        bookDetailPresentation.setAverageRating(ratingSummary.getAverageRating());
        bookDetailPresentation.setTotalVotes(ratingSummary.getTotalVotes());
        return  bookDetailPresentation;
    }

    @GetMapping(path="/books", produces = "application/json")
    public List<BookPresentation> listBooks() {
        List<BookPresentation> presentationBooks = new ArrayList<>();
        List<Book> books =  bookService.getBooks();
        for (Book book : books) {
            BookPresentation bookPresentation = new BookPresentation(book, ratingService.getAverageRating(book.getId()));
            presentationBooks.add(bookPresentation);
        }
        return presentationBooks;
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
