package bookReviewer.entity.offerHistory;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Offer {
    private BigDecimal price;

    private LocalDate requestDate;

    public Offer() {
    }

    public Offer(BigDecimal price, LocalDate requestDate) {
        this.price = price;
        this.requestDate = requestDate;
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

    @Override
    public String toString() {
        return "Offer{" +
                "price=" + price +
                ", requestDate=" + requestDate +
                '}';
    }
}
