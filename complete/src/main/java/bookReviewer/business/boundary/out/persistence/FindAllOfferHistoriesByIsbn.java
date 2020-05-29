package bookReviewer.business.boundary.out.persistence;

import java.util.List;

public interface FindAllOfferHistoriesByIsbn {
    List<bookReviewer.persistence.model.CachedOfferHistoryPersistence> findAllOffersByIsbn(String isbn);
}
