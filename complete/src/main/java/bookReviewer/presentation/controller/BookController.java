package bookReviewer.presentation.controller;

import bookReviewer.business.service.BookService;
import bookReviewer.business.service.OfferService;
import bookReviewer.business.service.RatingService;
import bookReviewer.business.model.RatingSummary;
import bookReviewer.persistence.model.Book;
import bookReviewer.presentation.mapper.BookMapper;
import bookReviewer.presentation.model.BookDetailPresentation;
import bookReviewer.presentation.model.BookPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @ResponseStatus( HttpStatus.CREATED
    )
    public Long createBook(@RequestBody @Valid Book book, @RequestHeader Map<String, String> headers){
        String token = headers.get("authorization");
        String cleanToken = null;
        if (token != null){
            String[] splittedToken = token.split(" ");
            cleanToken = splittedToken[1];

        }


        return bookService.createBook(book, cleanToken);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'MODERATOR')")
    @DeleteMapping(path = "/books/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        bookService.deleteBook(id);
    }


}
