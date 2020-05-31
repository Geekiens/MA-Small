package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.MediaTypeMapper;
import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.OfferHistoryMapper;
import bookReviewer.business.boundary.out.persistence.FindOfferHistory;
import bookReviewer.entity.offerHistory.MediaType;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindOfferHistoryService")
public class FindOfferHistoryService implements FindOfferHistory {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;

    public OfferHistroy findOfferHistory(String isbn, String vendor, MediaType mediaType){
        return OfferHistoryMapper.map(cachedOfferHistoryRepository.findByIsbnAndVendorAndMediaType(
                isbn,
                vendor,
                MediaTypeMapper.map(mediaType)));
    }
}