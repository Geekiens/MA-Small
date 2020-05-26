package bookReviewer.presentation.model;

import bookReviewer.business.model.Book;
import bookReviewer.business.model.RatingSummary;

public class BookPresentation {
    Long id;
    String title;
    String author;
    Double averageRating;
    int totalVotes;

    public BookPresentation(Book book, RatingSummary ratingSummary) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        if (!Double.isNaN(ratingSummary.getAverageRating())) {
            this.averageRating = ratingSummary.getAverageRating();
        }
        else {
            this.averageRating = new Double(0);
        }
        this.totalVotes = ratingSummary.getTotalVotes();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
}
