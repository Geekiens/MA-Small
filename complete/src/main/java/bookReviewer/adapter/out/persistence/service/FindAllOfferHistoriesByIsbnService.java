package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.persistenceToEntity.OfferHistoryMapper;
import bookReviewer.application.boundary.out.persistence.FindAllOfferHistoriesByIsbn;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.adapter.out.persistence.repository.OfferHistoryRepository;

import java.util.List;


public class FindAllOfferHistoriesByIsbnService implements FindAllOfferHistoriesByIsbn {

    OfferHistoryRepository offerHistoryRepository;

    public FindAllOfferHistoriesByIsbnService(OfferHistoryRepository offerHistoryRepository){
        this.offerHistoryRepository = offerHistoryRepository;
    }

    public List<OfferHistroy> findAllOffersByIsbn(String isbn){
        return OfferHistoryMapper.mapList(offerHistoryRepository.findByIsbn(isbn));
    }
}
