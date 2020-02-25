package bookReviewer.presentation.model;

import bookReviewer.business.model.Offer;

import java.util.ArrayList;
import java.util.List;

public class BookDetailPresentation {

    private String author;
    private String title;
    private String genre;
    private String[] keywords;
    private String[] languages;
    private String publisher;
    private int pages;
    private String isbn;
    private int publishingYear;
    private String content;

    private int totalVotes;
    private double averageRating;

    private List<Offer> hardcoverOffers;
    private List<Offer> paperbackOffers;
    private List<Offer> ebookOffers;
    private List<Offer> audiobookOffers;

    public BookDetailPresentation() {
        hardcoverOffers = new ArrayList<>();
        paperbackOffers = new ArrayList<>();
        ebookOffers = new ArrayList<>();
        audiobookOffers = new ArrayList<>();
    }

    public BookDetailPresentation(String author, String title, String genre, String[] keywords, String[] languages, String publisher, int pages, String isbn, int publishingYear, String content) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.keywords = keywords;
        this.languages = languages;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.publishingYear = publishingYear;
        this.content = content;

        hardcoverOffers = new ArrayList<>();
        paperbackOffers = new ArrayList<>();
        ebookOffers = new ArrayList<>();
        audiobookOffers = new ArrayList<>();
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

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Offer> getHardcoverOffers() {
        return hardcoverOffers;
    }

    public void setHardcoverOffers(List<Offer> hardcoverOffers) {
        this.hardcoverOffers = hardcoverOffers;
    }

    public List<Offer> getPaperbackOffers() {
        return paperbackOffers;
    }

    public void setPaperbackOffers(List<Offer> paperbackOffers) {
        this.paperbackOffers = paperbackOffers;
    }

    public List<Offer> getEbookOffers() {
        return ebookOffers;
    }

    public void setEbookOffers(List<Offer> ebookOffers) {
        this.ebookOffers = ebookOffers;
    }

    public List<Offer> getAudiobookOffers() {
        return audiobookOffers;
    }

    public void setAudiobookOffers(List<Offer> audiobookOffers) {
        this.audiobookOffers = audiobookOffers;
    }

    public void addHardcoverOffer(Offer offer) {
        hardcoverOffers.add(offer);
    }

    public void addPaperbackOffer(Offer offer) {
        paperbackOffers.add(offer);
    }

    public void addEbookOffer(Offer offer) {
        ebookOffers.add(offer);
    }

    public void addAudiobookOffer(Offer offer) {
        audiobookOffers.add(offer);
    }

}


