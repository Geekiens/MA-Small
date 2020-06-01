package bookReviewer.adapter.out.externalSystems.yourFavoriteBookVendor;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

@JsonIgnoreProperties
public class Rates {
    BigDecimal EUR;
    BigDecimal AUD;

    public Rates() {
    }

    public BigDecimal getEUR() {
        return EUR;
    }

    public void setEUR(BigDecimal EUR) {
        this.EUR = EUR;
    }

    public BigDecimal getAUD() {
        return AUD;
    }

    public void setAUD(BigDecimal AUD) {
        this.AUD = AUD;
    }
}