package bookReviewer.presentation.controller;


import bookReviewer.adapter.in.web.rating.NewRatingDTO;
import bookReviewer.adapter.in.web.rating.RatingDTO;
import bookReviewer.adapter.in.web.rating.RatingAdapter;
import bookReviewer.adapter.in.web.rating.UpdateRatingDTO;
import bookReviewer.business.model.RatingBusiness;
import bookReviewer.business.util.JwtProvider;
import bookReviewer.business.service.RatingService;
import bookReviewer.presentation.TokenFormatter;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("RatingAdapterService")
    RatingAdapter ratingAdapter;


    @GetMapping(path="/books/{bookId}/ratings", produces = "application/json")
    public List<RatingDTO> getRatingsByBookId(@PathVariable("bookId") long bookId) {
        return ratingAdapter.getRatingsOfBook(bookId);
    }

    @GetMapping(path="/books/{bookId}/ratings/content", produces = "application/json")
    public List<RatingDTO> getRatingsByBookIdContent(@PathVariable("bookId") long bookId) {
        return ratingAdapter.getRatingsOfBookWithContent(bookId);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'USER')")
    @PostMapping(path= "/books/{bookId}/ratings", consumes = "application/json", produces = "application/json")
    @ResponseStatus( HttpStatus.CREATED)
    public Long createRating(@PathVariable (value = "bookId") Long bookId,
                             @RequestBody @Valid NewRatingDTO rating,
                             @RequestHeader Map<String, String> headers){

        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        return ratingAdapter.createRating(bookId, rating, cleanToken);
    }
    @PutMapping(path="/books/{bookId}/ratings/{ratingId}", consumes = "application/json", produces = "application/json")
    public void updateRating (@PathVariable (value="bookId") long bookId,
                              @PathVariable (value="ratingId") long ratingId,
                              @RequestBody UpdateRatingDTO rating,
                              @RequestHeader Map<String, String> headers
                              ){
        if (!isOwnRating(headers, ratingId)){
            throw new AccessDeniedException("User doesn't match to the requested rating");
        }
        String cleanToken = TokenFormatter.format(headers.get("authorization"));

        ratingAdapter.updateRating(bookId, rating, cleanToken);
    }

    @PreAuthorize("hasRequiredRole(#headers, 'ADMIN')")
    @DeleteMapping(path = "/ratings/{id}")
    public void deleteBook(@PathVariable("id") long id,
                           @RequestHeader Map<String, String> headers) {
        String cleanToken = TokenFormatter.format(headers.get("authorization"));
        ratingAdapter.deleteRating(id, cleanToken);
    }

    public boolean isOwnRating(Map<String, String> headers, long ratingId) {
        System.out.println("ratingid" + ratingId);

        String token = headers.get("authorization");
        String[] splittedToken = token.split(" ");

        Claims decodedToken = JwtProvider.decodeJWT(splittedToken[1]);

        long userId = (long) (int) decodedToken.get("userId");
        RatingBusiness rating = ratingService.getRating(ratingId);

        return rating.getUserId() == userId;
    }


}
