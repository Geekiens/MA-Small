package bookReviewer.business.boundary.out.persistence;

import bookReviewer.entity.offerHistory.MediaType;
import bookReviewer.entity.offerHistory.OfferHistroy;

public interface FindOfferHistory {
    OfferHistroy findOfferHistory(String isbn, String vendor, MediaType mediaType);
}
