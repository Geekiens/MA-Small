package bookReviewer.presentation.controller;


import bookReviewer.adapter.in.web.rating.NewRating;
import bookReviewer.adapter.in.web.rating.Rating;
import bookReviewer.adapter.in.web.rating.RatingAdapter;
import bookReviewer.adapter.in.web.rating.UpdateRating;
import bookReviewer.presentation.controller.util.TokenFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class RatingController {

    @Autowired
    @Qualifier("RatingAdapterService")
    RatingAdapter ratingAdapter;


    @GetMapping(path="/books/{bookId}/ratings", produces = "application/json")
    public List<Rating> getRatingsByBookId(@PathVariable("bookId") long bookId) {
        return ratingAdapter.getRatingsOfBook(bookId);
    }

    @GetMapping(path="/books/{bookId}/ratings/content", produces = "application/json")
    public List<Rating> getRatingsByBookIdContent(@PathVariable("bookId") long bookId) {
        return ratingAdapter.getRatingsOfBookWithContent(bookId);
    }

    @PostMapping(path= "/books/{bookId}/ratings", consumes = "application/json", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED)
    public Long createRating(@PathVariable (value = "bookId") Long bookId,
                             @RequestBody @Valid NewRating rating,
                             @RequestHeader Map<String, String> headers){

        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        return ratingAdapter.createRating(bookId, rating, cleanToken);
    }
    @PutMapping(path="/books/{bookId}/ratings/{ratingId}", consumes = "application/json", produces = "application/json")
    public void updateRating (@PathVariable (value="bookId") long bookId,
                              @PathVariable (value="ratingId") long ratingId,
                              @RequestBody UpdateRating rating,
                              @RequestHeader Map<String, String> headers
                              ){
        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        ratingAdapter.updateRating(bookId, rating, cleanToken);
    }

    @DeleteMapping(path = "/ratings/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        ratingAdapter.deleteRating(id, cleanToken);
    }
}
