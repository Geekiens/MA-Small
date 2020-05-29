package bookReviewer.entity.book;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;

public class BookMetaDetails {
    @NotNull
    private String author;
    @NotNull
    private String title;
    private int pages;
    @PositiveOrZero
    private int publishingYear;
    private String[] languages;
    private String genre;
    private String publisher;

    public BookMetaDetails() {
    }

    public BookMetaDetails(String author, String title, int pages, int publishingYear, String[] languages, String genre, String publisher) {
        this.author = author;
        this.title = title;
        this.pages = pages;
        this.publishingYear = publishingYear;
        this.languages = languages;
        this.genre = genre;
        this.publisher = publisher;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "BookMetaDetails{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", publishingYear=" + publishingYear +
                ", languages=" + Arrays.toString(languages) +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}


