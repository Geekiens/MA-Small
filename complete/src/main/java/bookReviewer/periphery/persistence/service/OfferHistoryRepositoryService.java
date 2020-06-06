package bookReviewer.periphery.persistence.service;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.adapter.out.persistence.model.MediaType;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;
import bookReviewer.periphery.persistence.repository.OfferHistoryRepositoryJpa;


import java.util.List;

public class OfferHistoryRepositoryService implements OfferHistoryRepository {

    OfferHistoryRepositoryJpa offerHistoryRepositoryJpa;

    public OfferHistoryRepositoryService(OfferHistoryRepositoryJpa offerHistoryRepositoryJpa){
        this.offerHistoryRepositoryJpa = offerHistoryRepositoryJpa;
    }

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
