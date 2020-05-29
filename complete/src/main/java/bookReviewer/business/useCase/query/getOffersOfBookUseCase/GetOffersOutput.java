package bookReviewer.business.useCase.query.getOffersOfBookUseCase;

import bookReviewer.business.shared.MediaType;

import java.math.BigDecimal;

public class GetOffersOutput {
    BigDecimal price;
    String vendor;
    String affiliateLink;
    MediaType mediaType;

    public GetOffersOutput(BigDecimal price, String vendor, String affiliateLink, MediaType mediaType) {
        this.price = price;
        this.vendor = vendor;
        this.affiliateLink = affiliateLink;
        this.mediaType = mediaType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAffiliateLink() {
        return affiliateLink;
    }

    public void setAffiliateLink(String affiliateLink) {
        this.affiliateLink = affiliateLink;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "price=" + price +
                ", vendor='" + vendor + '\'' +
                ", affiliateLink='" + affiliateLink + '\'' +
                ", mediaType=" + mediaType +
                '}';
    }
}



// Beim Speichern Verknüpfung zum Buch nötig z.B. über ISBN

