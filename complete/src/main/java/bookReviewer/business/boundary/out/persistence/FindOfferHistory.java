package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.MediaType;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;

public interface FindOfferHistory {
    CachedOfferHistoryPersistence findOfferHistory(String isbn, String vendor, MediaType mediaType);
}
