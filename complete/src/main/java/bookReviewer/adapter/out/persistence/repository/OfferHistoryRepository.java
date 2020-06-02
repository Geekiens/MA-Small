package bookReviewer.adapter.out.persistence.repository;

import bookReviewer.adapter.out.persistence.model.CachedOfferHistory;
import bookReviewer.adapter.out.persistence.model.MediaType;

import java.util.List;

public interface OfferHistoryRepository {
    List<CachedOfferHistory> findByIsbn(String isbn);
    CachedOfferHistory findByIsbnAndVendorAndMediaType(String isbn, String vendor, MediaType mediaType);
    void save(CachedOfferHistory cachedOfferHistory);
}
