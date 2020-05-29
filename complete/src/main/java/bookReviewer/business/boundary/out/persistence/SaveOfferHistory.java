package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.CachedOfferHistoryPersistence;

public interface SaveOfferHistory {
    void saveOfferHistory(CachedOfferHistoryPersistence offerHistoryPersistence);
}
