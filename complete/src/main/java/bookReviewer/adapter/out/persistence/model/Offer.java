package bookReviewer.adapter.out.persistence.model;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="OFFER")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cachedOfferHistory_id", nullable = false)
    private CachedOfferHistory cachedOfferHistory;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="requestDate")
    private LocalDate requestDate;


    public Offer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CachedOfferHistory getCachedOfferHistory() {
        return cachedOfferHistory;
    }

    public void setCachedOfferHistory(CachedOfferHistory cachedOfferHistory) {
        this.cachedOfferHistory = cachedOfferHistory;
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
