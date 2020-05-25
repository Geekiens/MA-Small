package bookReviewer.presentation.controller;

import bookReviewer.business.boundary.in.useCase.command.CreateBookCommand;
import bookReviewer.business.boundary.in.useCase.command.DeleteBookCommand;
import bookReviewer.business.boundary.in.useCase.query.GetBookQuery;
import bookReviewer.business.boundary.in.useCase.query.GetBooksQuery;
import bookReviewer.business.boundary.in.useCase.query.GetOffersOfBookQuery;
import bookReviewer.business.model.Book;
import bookReviewer.business.model.BookBusiness;
import bookReviewer.business.service.RatingService;
import bookReviewer.presentation.mapper.BookMapper;
import bookReviewer.presentation.model.BookDetailPresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    @Qualifier("GetOffersOfBookQueryImpl")
    GetOffersOfBookQuery getOffersOfBookQuery;

    @Autowired
    @Qualifier("CreateBookCommandImpl")
    CreateBookCommand createBookCommand;

    @Autowired
    @Qualifier("DeleteBookCommandImpl")
    DeleteBookCommand deleteBookCommand;

    @Autowired
    @Qualifier("GetBookQueryImpl")
    GetBookQuery getBookQuery;

    @Autowired
    @Qualifier("GetBooksQueryImpl")
    GetBooksQuery getBooksQuery;

    @Autowired
    RatingService ratingService;

    BookMapper bookMapper = new BookMapper();

    @GetMapping(path="/books/{id}", produces = "application/json")
    public BookDetailPresentation getBook(@PathVariable("id") long id) {
        BookDetailPresentation bookDetailPresentation = bookMapper.map(getBookQuery.getBook(id), getOffersOfBookQuery.getOffers(id));

        return  bookDetailPresentation;
    }

    @GetMapping(path="/books", produces = "application/json")
    public List<Book> listBooks() {
        List<Book> books =  getBooksQuery.getBooks();

        return books;
    }

    @PostMapping(path= "/books", consumes = "application/json", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED
    )
    public Long createBook(@RequestBody @Valid BookBusiness book, @RequestHeader Map<String, String> headers){
        String token = headers.get("authorization");
        String cleanToken = null;
        if (token != null){
            String[] splittedToken = token.split(" ");
            cleanToken = splittedToken[1];

        }


        return createBookCommand.createBook(book, cleanToken);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'MODERATOR')")
    @DeleteMapping(path = "/books/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        deleteBookCommand.deleteBook(id);
    }


}
