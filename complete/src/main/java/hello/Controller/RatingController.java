package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RatingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/rating")
    public Rating rating(@RequestParam(value="name", defaultValue="World") String name) {
        return new Rating(1, "Title", "daesgfsaerg", "Max");
    }
}
