package bookReviewer.entity.offerHistory;

import java.util.ArrayList;

public class OfferHistroy {
    private long id;
    private long bookId;
    private ArrayList<Offer> offers;
    private Vendor vendor;
    private MediaType mediaType;

    public OfferHistroy() {
    }

    public OfferHistroy(long id, long bookId, ArrayList<Offer> offers, Vendor vendor, MediaType mediaType) {
        this.id = id;
        this.bookId = bookId;
        this.offers = offers;
        this.vendor = vendor;
        this.mediaType = mediaType;
    }

    public OfferHistroy(long bookId, ArrayList<Offer> offers, Vendor vendor, MediaType mediaType) {
        this.bookId = bookId;
        this.offers = offers;
        this.vendor = vendor;
        this.mediaType = mediaType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "OfferHistroy{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", offers=" + offers.toString() +
                ", vendor=" + vendor.toString() +
                ", mediaType=" + mediaType +
                '}';
    }
}
