package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
/*
        RatingDao ratingDao = new RatingDao();
        Rating rating = new Rating(1, "Title", "daesgfsaerg", "Max");
        ratingDao.saveRating(rating);
        List< Rating > ratings = ratingDao.getRatings();
        System.out.println(ratings.size());
        System.out.println(ratings.get(0).getAuthor());

 */

    }
}
