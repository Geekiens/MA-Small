package bookReviewer.business.model;

import java.util.ArrayList;
import java.util.List;

public class Book extends bookReviewer.persistence.model.Book {
    private List<Offer> hardcoverOffers;
    private List<Offer> paperbackOffers;
    private List<Offer> ebookOffers;
    private List<Offer> audiobookOffers;

    Book() {
        super();
        hardcoverOffers = new ArrayList<>();
        paperbackOffers = new ArrayList<>();
        ebookOffers = new ArrayList<>();
        audiobookOffers = new ArrayList<>();
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
