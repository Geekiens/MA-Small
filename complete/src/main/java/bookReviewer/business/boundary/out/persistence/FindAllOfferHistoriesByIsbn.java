package bookReviewer.business.boundary.out.persistence;

import bookReviewer.persistence.model.CachedOfferHistoryPersistence;

import java.util.List;

public interface FindAllOfferHistoriesByIsbn {
    List<CachedOfferHistoryPersistence> findAllOffersByIsbn(String isbn);
}
