package bookReviewer.business.model;

public class RatingSummary {
    Double averageRating;
    int totalVotes = 0;

    public RatingSummary() {
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public  void addTotalVotes() {
        this.totalVotes ++;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
