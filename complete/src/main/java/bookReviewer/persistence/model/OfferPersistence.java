package bookReviewer.persistence.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="OFFER")
public class OfferPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cachedOfferHistoryPersistence_id", nullable = false)
    private CachedOfferHistoryPersistence cachedOfferHistoryPersistence;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="requestDate")
    private LocalDate requestDate;


    public OfferPersistence() {
    }

    public OfferPersistence(BigDecimal price, LocalDate requestDate) {
        this.price = price;
        this.requestDate = requestDate;
    }

    public OfferPersistence(CachedOfferHistoryPersistence cachedOfferHistoryPersistence, BigDecimal price, LocalDate requestDate) {
        this.cachedOfferHistoryPersistence = cachedOfferHistoryPersistence;
        this.price = price;
        this.requestDate = requestDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CachedOfferHistoryPersistence getCachedOfferHistoryPersistence() {
        return cachedOfferHistoryPersistence;
    }

    public void setCachedOfferHistoryPersistence(CachedOfferHistoryPersistence cachedOfferHistoryPersistence) {
        this.cachedOfferHistoryPersistence = cachedOfferHistoryPersistence;
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
}
