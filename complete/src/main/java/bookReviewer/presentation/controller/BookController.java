package bookReviewer.presentation.controller;

import bookReviewer.adapter.in.web.book.BookAdapter;
import bookReviewer.adapter.in.web.book.BookWithOffersDTO;
import bookReviewer.adapter.in.web.book.BookWithRatingInformationDTO;
import bookReviewer.adapter.in.web.book.NewBookDTO;
import bookReviewer.presentation.TokenFormatter;

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
    @Qualifier("BookAdapterService")
    BookAdapter bookAdapter;


    @GetMapping(path="/books/{id}", produces = "application/json")
    public BookWithOffersDTO getBook(@PathVariable("id") long id) {
        return  bookAdapter.getBook(id);
    }

    @GetMapping(path="/books", produces = "application/json")
    public List<BookWithRatingInformationDTO> listBooks() {
        return bookAdapter.getBooks();
    }

    @PostMapping(path= "/books", consumes = "application/json", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED
    )
    public Long createBook(@RequestBody @Valid NewBookDTO book, @RequestHeader Map<String, String> headers){
        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        return bookAdapter.createBook(book, cleanToken);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'MODERATOR')")
    @DeleteMapping(path = "/books/{id}")
    public void deleteBook(@PathVariable("id") long id, @RequestHeader Map<String, String> headers) {
        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        bookAdapter.deleteBook(id, cleanToken);
    }


}
