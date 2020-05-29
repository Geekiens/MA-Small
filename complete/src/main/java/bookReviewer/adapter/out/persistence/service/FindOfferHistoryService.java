package bookReviewer.adapter.out.persistence.service;

import bookReviewer.business.boundary.out.persistence.FindOfferHistory;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.persistence.model.MediaType;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindOfferHistoryService")
public class FindOfferHistoryService implements FindOfferHistory {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;

    public CachedOfferHistoryPersistence findOfferHistory(String isbn, String vendor, MediaType mediaType){
        return cachedOfferHistoryRepository.findByIsbnAndVendorAndMediaType(isbn, vendor, mediaType);
    }
}
