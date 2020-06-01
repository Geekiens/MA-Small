package bookReviewer.adapter.in.web.book;

import bookReviewer.business.useCase.query.getOffersOfBookUseCase.OfferOutput;

import java.util.ArrayList;
import java.util.List;

public class BookWithOffers {

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

    private List<OfferOutput> hardcoverOffers;
    private List<OfferOutput> paperbackOffers;
    private List<OfferOutput> ebookOffers;
    private List<OfferOutput> audiobookOffers;

    public BookWithOffers() {
        hardcoverOffers = new ArrayList<>();
        paperbackOffers = new ArrayList<>();
        ebookOffers = new ArrayList<>();
        audiobookOffers = new ArrayList<>();
    }

    public BookWithOffers(String author,
                          String title,
                          String genre,
                          String[] keywords,
                          String[] languages,
                          String publisher,
                          int pages,
                          String isbn,
                          int publishingYear,
                          String content,
                          int totalVotes,
                          double averageRating) {
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
        this.totalVotes = totalVotes;
        this.averageRating = averageRating;

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
        if (Double.isNaN(averageRating)){
            this.averageRating = 0;
            return;
        }
        this.averageRating = averageRating;
    }

    public List<OfferOutput> getHardcoverOffers() {
        return hardcoverOffers;
    }

    public void setHardcoverOffers(List<OfferOutput> hardcoverOffers) {
        this.hardcoverOffers = hardcoverOffers;
    }

    public List<OfferOutput> getPaperbackOffers() {
        return paperbackOffers;
    }

    public void setPaperbackOffers(List<OfferOutput> paperbackOffers) {
        this.paperbackOffers = paperbackOffers;
    }

    public List<OfferOutput> getEbookOffers() {
        return ebookOffers;
    }

    public void setEbookOffers(List<OfferOutput> ebookOffers) {
        this.ebookOffers = ebookOffers;
    }

    public List<OfferOutput> getAudiobookOffers() {
        return audiobookOffers;
    }

    public void setAudiobookOffers(List<OfferOutput> audiobookOffers) {
        this.audiobookOffers = audiobookOffers;
    }

    public void addHardcoverOffer(OfferOutput offer) {
        hardcoverOffers.add(offer);
    }

    public void addPaperbackOffer(OfferOutput offer) {
        paperbackOffers.add(offer);
    }

    public void addEbookOffer(OfferOutput offer) {
        ebookOffers.add(offer);
    }

    public void addAudiobookOffer(OfferOutput offer) {
        audiobookOffers.add(offer);
    }

}


