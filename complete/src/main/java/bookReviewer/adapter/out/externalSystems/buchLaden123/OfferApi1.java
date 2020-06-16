package bookReviewer.adapter.out.externalSystems.buchLaden123;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class OfferApi1 {

    @JsonIgnoreProperties
    Long price; // in Cents
    String affiliate;
    String media;

    public OfferApi1() {
    }

    public OfferApi1(Long price, String affiliate, String media) {
        this.price = price;
        this.affiliate = affiliate;
        this.media = media;
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

    @Override
    public String toString() {
        return "OfferApi1{" +
                "price=" + price +
                ", affiliate='" + affiliate + '\'' +
                ", media='" + media + '\'' +
                '}';
    }
}
