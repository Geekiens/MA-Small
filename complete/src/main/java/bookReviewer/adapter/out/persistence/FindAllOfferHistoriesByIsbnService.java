package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.FindAllOfferHistoriesByIsbn;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import bookReviewer.persistence.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FindAllOfferHistoriesByIsbnService implements FindAllOfferHistoriesByIsbn {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;


}
