package bookReviewer.entity.rating;

import java.util.List;

public class AverageRatingCalculatorService {
    private List<Rating> ratings;
    private int totalVotes = 0;
    private int sumOfRatings = 0;

    public AverageRatingCalculatorService(List<Rating> ratings) {
        this.ratings = ratings;
        calculate();
    }

    public Double getAverageRating(){
        return sumOfRatings  * 1.0 / totalVotes;
    }

    public int getTotalVotes(){
        return totalVotes;
    }

    private void calculate(){
        for (Rating rating: ratings){
            sumOfRatings += rating.getRatingDetails().getScore();
            totalVotes++;
        }
    }
}

