package bookReviewer.persistence.model;

import bookReviewer.business.model.MediaType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="requestDate")
    private LocalDate requestDate;

    @Column(name="vendor")
    private String vendor;

    @Column(name="mediaType")
    private MediaType mediaType;

    public OfferPersistence() {
    }

    public OfferPersistence(BigDecimal price, LocalDate requestDate, String vendor, MediaType mediaType) {
        this.price = price;
        this.requestDate = requestDate;
        this.vendor = vendor;
        this.mediaType = mediaType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
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
}
