package bookReviewer.presentation.controller;

import bookReviewer.business.boundary.in.useCase.command.CreateBookUseCase;
import bookReviewer.business.boundary.in.useCase.command.DeleteBookUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetBookUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetBooksUseCase;
import bookReviewer.business.boundary.in.useCase.query.GetOffersOfBookUseCase;
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
    @Qualifier("GetOffersOfBookService")
    GetOffersOfBookUseCase getOffersOfBookUseCase;

    @Autowired
    @Qualifier("CreateBookService")
    CreateBookUseCase createBookUseCase;

    @Autowired
    @Qualifier("DeleteBookService")
    DeleteBookUseCase deleteBookUseCase;

    @Autowired
    @Qualifier("GetBookService")
    GetBookUseCase getBookUseCase;

    @Autowired
    @Qualifier("GetBooksService")
    GetBooksUseCase getBooksUseCase;

    @Autowired
    RatingService ratingService;

    BookMapper bookMapper = new BookMapper();

    @GetMapping(path="/books/{id}", produces = "application/json")
    public BookDetailPresentation getBook(@PathVariable("id") long id) {
        BookDetailPresentation bookDetailPresentation = bookMapper.map(getBookUseCase.getBook(id), getOffersOfBookUseCase.getOffers(id));

        return  bookDetailPresentation;
    }

    @GetMapping(path="/books", produces = "application/json")
    public List<Book> listBooks() {
        List<Book> books =  getBooksUseCase.getBooks();

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


        return createBookUseCase.createBook(book, cleanToken);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'MODERATOR')")
    @DeleteMapping(path = "/books/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        deleteBookUseCase.deleteBook(id);
    }


}
