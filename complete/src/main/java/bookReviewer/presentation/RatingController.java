package bookReviewer.presentation;


import bookReviewer.business.RatingService;
import bookReviewer.persistence.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path= "/books/{bookId}/ratings", consumes = "application/json", produces = "application/json")
    public void createRating(@PathVariable (value = "bookId") Long bookId,
                             @RequestBody Rating rating,
                             @RequestHeader Map<String, String> headers){
        String token = headers.get("authorization");
        String[] splittedToken = token.split(" ");


        ratingService.createRating(bookId, rating, splittedToken[1]);
    }

}
