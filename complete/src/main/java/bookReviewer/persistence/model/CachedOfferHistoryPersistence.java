package bookReviewer.persistence.model;

import bookReviewer.business.model.MediaType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="CACHED_OFFER_HISTORY")
public class CachedOfferHistoryPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="vendor")
    private String vendor;

    @Column(name="mediaType")
    private MediaType mediaType;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "cachedOfferHistoryPersistence")
    private ArrayList<OfferPersistence> offers = new ArrayList<>();

    public CachedOfferHistoryPersistence() {
    }

    public CachedOfferHistoryPersistence(String vendor, MediaType mediaType, ArrayList<OfferPersistence> offers) {
        this.vendor = vendor;
        this.mediaType = mediaType;
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

    public ArrayList<OfferPersistence> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<OfferPersistence> offers) {
        this.offers = offers;
    }

    public void addOffer(OfferPersistence offer) {
        this.offers.add(offer);
    }
}
