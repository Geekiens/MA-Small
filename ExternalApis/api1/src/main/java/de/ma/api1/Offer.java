package de.ma.api1;

import java.math.BigDecimal;

public class Offer {
    Long price; // in Cents
    String affiliate;
    String media;
    String isbn;

    public Offer(Long price, String affiliate, String media, String isbn) {
        this.price = price;
        this.affiliate = affiliate;
        this.media = media;
        this.isbn = isbn;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getAffiliate() {
        return affiliate;
    }

    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
