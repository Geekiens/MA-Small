package bookReviewer.business.boundary.out.persistence;

import java.util.ArrayList;

public interface FindAllOfferHistoriesByIsbn {
    ArrayList<bookReviewer.persistence.model.CachedOfferHistoryPersistence> findAllOffersByIsbn(String isbn);
}
