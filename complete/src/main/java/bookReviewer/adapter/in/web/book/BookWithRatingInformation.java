package bookReviewer.adapter.in.web.book;

import java.util.Arrays;

public class BookWithRatingInformation {
    public long id;
    public String author;
    public String title;
    public String genre;
    public String[] keywords;
    public String[] languages;
    public  String publisher;
    public int pages;
    public String isbn;
    public int publishingYear;
    public String content;
    public Double averageRating;
    public int totalVotes = 0;
    public int favoriteCounter;

    public BookWithRatingInformation() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getFavoriteCounter() {
        return favoriteCounter;
    }

    public void setFavoriteCounter(int favoriteCounter) {
        this.favoriteCounter = favoriteCounter;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", keywords=" + Arrays.toString(keywords) +
                ", languages=" + Arrays.toString(languages) +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", isbn='" + isbn + '\'' +
                ", publishingYear=" + publishingYear +
                ", content='" + content + '\'' +
                ", averageRating=" + averageRating +
                ", totalVotes=" + totalVotes +
                '}';
    }
}
