package bookReviewer.presentation.controller;


import bookReviewer.business.util.JwtProvider;
import bookReviewer.business.service.RatingService;
import bookReviewer.persistence.model.Rating;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class RatingController {

    @Autowired
    RatingService ratingService = new RatingService();


    @GetMapping(path="/books/{bookId}/ratings", produces = "application/json")
    public List<Rating> getRatingsByBookId(@PathVariable("bookId") long bookId) {
        return ratingService.findRatingsById(bookId);
    }

    @GetMapping(path="/books/{bookId}/ratings/content", produces = "application/json")
    public List<Rating> getRatingsByBookIdContent(@PathVariable("bookId") long bookId) {
        return ratingService.getRatingsByIdWithContent(bookId);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'USER')")
    @PostMapping(path= "/books/{bookId}/ratings", consumes = "application/json", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED)
    public Long createRating(@PathVariable (value = "bookId") Long bookId,
                             @RequestBody @Valid Rating rating,
                             @RequestHeader Map<String, String> headers){
        String token = headers.get("authorization");
        String[] splittedToken = token.split(" ");

        return ratingService.createRating(bookId, rating, splittedToken[1]);
    }
    @PutMapping(path="/books/{bookId}/ratings/{ratingId}", consumes = "application/json", produces = "application/json")
    public void updateRating (@PathVariable (value="bookId") long bookId,
                              @PathVariable (value="ratingId") long ratingId,
                              @RequestBody Rating rating,
                              @RequestHeader Map<String, String> headers
                              ){
        if (!isOwnRating(headers, ratingId)){
            throw new AccessDeniedException("User doesn't match to the requested rating");
        }

        ratingService.updateRating(bookId, rating);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'ADMIN')")
    @DeleteMapping(path = "/ratings/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        ratingService.deleteRating(id);
    }

    public boolean isOwnRating(Map<String, String> headers, long ratingId) {
        System.out.println("ratingid" + ratingId);

        String token = headers.get("authorization");
        String[] splittedToken = token.split(" ");

        Claims decodedToken = JwtProvider.decodeJWT(splittedToken[1]);

        long userId = (long) (int) decodedToken.get("userId");
        Rating rating = ratingService.getRating(ratingId);

        return rating.getUserId() == userId;
    }


}
