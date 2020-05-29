package bookReviewer.adapter.out.persistence;

import bookReviewer.business.boundary.out.persistence.SaveOfferHistory;
import bookReviewer.persistence.model.CachedOfferHistoryPersistence;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveOfferHistoryService")
public class SaveOfferHistoryService implements SaveOfferHistory {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;

    public void saveOfferHistory(CachedOfferHistoryPersistence offerHistoryPersistence){
        cachedOfferHistoryRepository.save(offerHistoryPersistence);
    }
}
