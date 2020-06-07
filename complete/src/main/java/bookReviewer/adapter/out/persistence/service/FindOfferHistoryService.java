package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.MediaTypeMapper;
import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.OfferHistoryMapper;
import bookReviewer.application.boundary.out.persistence.FindOfferHistory;
import bookReviewer.entity.offerHistory.MediaType;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;

public class FindOfferHistoryService implements FindOfferHistory {

    OfferHistoryRepository offerHistoryRepository;

    public FindOfferHistoryService(OfferHistoryRepository offerHistoryRepository){
        this.offerHistoryRepository = offerHistoryRepository;
    }

    public OfferHistroy findOfferHistory(String isbn, String vendor, MediaType mediaType){
        return OfferHistoryMapper.map(offerHistoryRepository.findByIsbnAndVendorAndMediaType(
                isbn,
                vendor,
                MediaTypeMapper.map(mediaType)));
    }
}
