package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindAllOfferHistoriesByIsbn;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import bookReviewer.persistence.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindAllOfferHistoriesByIsbnService")
public class FindAllOfferHistoriesByIsbnService implements FindAllOfferHistoriesByIsbn {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;

    public List<CachedOfferHistoryPersistence> findAllOffersByIsbn(String isbn){
        return cachedOfferHistoryRepository.findByIsbn(isbn);
    }
}
