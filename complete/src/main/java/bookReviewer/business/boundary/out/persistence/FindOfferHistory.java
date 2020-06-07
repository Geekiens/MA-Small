package bookReviewer.business.boundary.out.persistence;

import bookReviewer.business.shared.MediaType;

public interface FindOfferHistory {
    bookReviewer.persistence.model.CachedOfferHistoryPersistence findOfferHistory(String isbn, String vendor, MediaType mediaType);
}
