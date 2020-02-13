package de.ma.currency;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

public class Rates {
    BigDecimal EUR;
    BigDecimal AUD;

    public Rates() {
    }

    public Rates(BigDecimal EUR, BigDecimal AUD) {
        this.EUR = EUR;
        this.AUD = AUD;
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