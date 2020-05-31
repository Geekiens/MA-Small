package bookReviewer.adapter.out.persistence.service;

import bookReviewer.adapter.out.persistence.mapping.entityToPersistence.OfferHistoryMapper;
import bookReviewer.business.boundary.out.persistence.SaveOfferHistory;
import bookReviewer.entity.offerHistory.OfferHistroy;
import bookReviewer.persistence.model.Book;
import bookReviewer.persistence.repository.BookRepository;
import bookReviewer.persistence.repository.CachedOfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SaveOfferHistoryService")
public class SaveOfferHistoryService implements SaveOfferHistory {
    @Autowired
    CachedOfferHistoryRepository cachedOfferHistoryRepository;

    @Autowired
    BookRepository bookRepository;

    public void saveOfferHistory(OfferHistroy offerHistroy){
        Book book = bookRepository.findById(offerHistroy.getBookId()).orElse(null);
        String isbn = null;
        if (book != null){
            isbn = book.getIsbn();
        }
        cachedOfferHistoryRepository.save(OfferHistoryMapper.map(offerHistroy, isbn));
    }
}
