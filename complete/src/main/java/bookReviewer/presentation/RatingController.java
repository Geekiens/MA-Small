package bookReviewer.presentation;


import bookReviewer.persistence.model.Rating;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingController {


    @RequestMapping(value = "/rating", method = RequestMethod.GET)
    public Rating rating(@RequestParam(value="name", defaultValue="World") String name) {
        return new Rating(1, "Title", "Author", 1L);
    }
}
