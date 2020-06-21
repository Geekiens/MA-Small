package bookReviewer.adapter.out.persistence.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="CACHED_OFFER_HISTORY")
public class CachedOfferHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="vendor")
    private String vendor;

    @Column(name="mediaType")
    private MediaType mediaType;

    @Column(name="isbn")
    private String isbn;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "cachedOfferHistory")
    private List<Offer> offers = new ArrayList<>();

    public CachedOfferHistory() {
    }

    public CachedOfferHistory(String vendor, MediaType mediaType, String isbn, ArrayList<Offer> offers) {
        this.vendor = vendor;
        this.mediaType = mediaType;
        this.isbn = isbn;
        this.offers = offers;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public void addOffer(Offer offer) {
        this.offers.add(offer);
    }
}