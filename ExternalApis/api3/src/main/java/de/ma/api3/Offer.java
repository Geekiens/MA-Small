package de.ma.api3;

import java.math.BigDecimal;

public class Offer {
    BigDecimal price;
    String currency;
    int type;
    String affiliate;

    public Offer(BigDecimal price, String currency, int type, String affiliate) {
        this.price = price;
        this.currency = currency;
        this.type = type;
        this.affiliate = affiliate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }
}


