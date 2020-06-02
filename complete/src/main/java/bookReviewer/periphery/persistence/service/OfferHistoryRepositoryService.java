package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.adapter.out.persistence.model.MediaType;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;
import bookReviewer.periphery.persistence.repository.OfferHistoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Qualifier("OfferHistoryRepositoryService")
public class OfferHistoryRepositoryService implements OfferHistoryRepository {

    @Autowired
    OfferHistoryRepositoryJpa offerHistoryRepositoryJpa;

    public List<CachedOfferHistory> findByIsbn(String isbn){
        return offerHistoryRepositoryJpa.findByIsbn(isbn);
    }
    public CachedOfferHistory findByIsbnAndVendorAndMediaType(String isbn, String vendor, MediaType mediaType){
        return offerHistoryRepositoryJpa.findByIsbnAndVendorAndMediaType(isbn, vendor, mediaType);
    }
    public void save(CachedOfferHistory cachedOfferHistory){
        offerHistoryRepositoryJpa.save(cachedOfferHistory);
    }
}
